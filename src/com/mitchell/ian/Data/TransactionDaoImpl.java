package com.mitchell.ian.Data;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    Connection connection;

    public TransactionDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Transaction getTransaction(int id) {
        Transaction retrievedTransaction = null;
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM account WHERE  id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            retrievedTransaction = new Transaction(
                    resultSet.getDouble("amount"),
                    accountDao.getAccount(resultSet.getInt("from_account")),
                    accountDao.getAccount(resultSet.getInt("to_account")),
                    resultSet.getString("memo"),
                    userDao.getUser(resultSet.getInt("initiated_by")),
                    resultSet.getDate("date_initiated"),
                    resultSet.getBoolean("require_user_approval"),
                    resultSet.getInt("id"),
                    userDao.getUser(resultSet.getInt("completed_by")),
                    resultSet.getDate("date_completed"),
                    resultSet.getBoolean("pending")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedTransaction;
    }

    @Override
    public List<Transaction> getAllTransactions(User user) {
        List<Transaction> transactionList = new ArrayList<>();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM transactions WHERE initiated_by = ? OR  completed_by = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction retrievedTransaction = new Transaction(
                        resultSet.getDouble("amount"),
                        accountDao.getAccount(resultSet.getInt("from_account")),
                        accountDao.getAccount(resultSet.getInt("to_account")),
                        resultSet.getString("memo"),
                        userDao.getUser(resultSet.getInt("initiated_by")),
                        resultSet.getDate("date_initiated"),
                        resultSet.getBoolean("require_user_approval"),
                        resultSet.getInt("id"),
                        userDao.getUser(resultSet.getInt("completed_by")),
                        resultSet.getDate("date_completed"),
                        resultSet.getBoolean("pending")
                );
                transactionList.add(retrievedTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getAllTransactions(Account account) {
        List<Transaction> transactionList = new ArrayList<>();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM transactions WHERE (from_account = ? OR to_account = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, account.getId());
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction retrievedTransaction = new Transaction(
                        resultSet.getDouble("amount"),
                        accountDao.getAccount(resultSet.getInt("from_account")),
                        accountDao.getAccount(resultSet.getInt("to_account")),
                        resultSet.getString("memo"),
                        userDao.getUser(resultSet.getInt("initiated_by")),
                        resultSet.getDate("date_initiated"),
                        resultSet.getBoolean("require_user_approval"),
                        resultSet.getInt("id"),
                        userDao.getUser(resultSet.getInt("completed_by")),
                        resultSet.getDate("date_completed"),
                        resultSet.getBoolean("pending")
                );
                transactionList.add(retrievedTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM transactions";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction retrievedTransaction = new Transaction(
                        resultSet.getDouble("amount"),
                        accountDao.getAccount(resultSet.getInt("from_account")),
                        accountDao.getAccount(resultSet.getInt("to_account")),
                        resultSet.getString("memo"),
                        userDao.getUser(resultSet.getInt("initiated_by")),
                        resultSet.getDate("date_initiated"),
                        resultSet.getBoolean("require_user_approval"),
                        resultSet.getInt("id"),
                        userDao.getUser(resultSet.getInt("completed_by")),
                        resultSet.getDate("date_completed"),
                        resultSet.getBoolean("pending")
                );
                transactionList.add(retrievedTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getAllPendingTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM transactions WHERE pending = true";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction retrievedTransaction = new Transaction(
                        resultSet.getDouble("amount"),
                        accountDao.getAccount(resultSet.getInt("from_account")),
                        accountDao.getAccount(resultSet.getInt("to_account")),
                        resultSet.getString("memo"),
                        userDao.getUser(resultSet.getInt("initiated_by")),
                        resultSet.getDate("date_initiated"),
                        resultSet.getBoolean("require_user_approval"),
                        resultSet.getInt("id"),
                        userDao.getUser(resultSet.getInt("completed_by")),
                        resultSet.getDate("date_completed"),
                        resultSet.getBoolean("pending")
                );
                transactionList.add(retrievedTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getAllPendingTransactions(User user) {
        List<Transaction> transactionList = new ArrayList<>();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM transactions WHERE (initiated_by = ? OR  completed_by = ?) AND pending = true";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction retrievedTransaction = new Transaction(
                        resultSet.getDouble("amount"),
                        accountDao.getAccount(resultSet.getInt("from_account")),
                        accountDao.getAccount(resultSet.getInt("to_account")),
                        resultSet.getString("memo"),
                        userDao.getUser(resultSet.getInt("initiated_by")),
                        resultSet.getDate("date_initiated"),
                        resultSet.getBoolean("require_user_approval"),
                        resultSet.getInt("id"),
                        userDao.getUser(resultSet.getInt("completed_by")),
                        resultSet.getDate("date_completed"),
                        resultSet.getBoolean("pending")
                );
                transactionList.add(retrievedTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public List<Transaction> getAllPendingTransactions(Account account) {
        List<Transaction> transactionList = new ArrayList<>();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        String sql = "SELECT * FROM transactions WHERE (from_account = ? OR to_account = ?) AND pending = true";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, account.getId());
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction retrievedTransaction = new Transaction(
                        resultSet.getDouble("amount"),
                        accountDao.getAccount(resultSet.getInt("from_account")),
                        accountDao.getAccount(resultSet.getInt("to_account")),
                        resultSet.getString("memo"),
                        userDao.getUser(resultSet.getInt("initiated_by")),
                        resultSet.getDate("date_initiated"),
                        resultSet.getBoolean("require_user_approval"),
                        resultSet.getInt("id"),
                        userDao.getUser(resultSet.getInt("completed_by")),
                        resultSet.getDate("date_completed"),
                        resultSet.getBoolean("pending")
                );
                transactionList.add(retrievedTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (amount, from_account, to_account, initiated_by, date_initiated) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, transaction.getAmount());
            preparedStatement.setInt(2, transaction.getFromAccount().getId());
            preparedStatement.setInt(3, transaction.getToAccount().getId());
            preparedStatement.setInt(4, transaction.getInitiatedBy().getId());
            preparedStatement.setDate(5, new java.sql.Date(transaction.getDateInitiated().getTime()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            transaction.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transactions " +
                "SET " +
                "amount = ?, " +
                "from_account = ?, " +
                "to_account = ?, " +
                "initiated_by = ?, " +
                "date_initiated = ?, " +
                "completed_by = ?, " +
                "date_completed = ?, " +
                "require_user_approval = ?, " +
                "pending = ?, " +
                "memo = ? " +
                "WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, transaction.getAmount());
            preparedStatement.setInt(2, transaction.getFromAccount().getId());
            preparedStatement.setInt(3, transaction.getToAccount().getId());
            preparedStatement.setInt(4, transaction.getInitiatedBy().getId());
            preparedStatement.setDate(5, new java.sql.Date(transaction.getDateInitiated().getTime()));
            preparedStatement.setInt(6, transaction.getCompletedBy().getId());
            preparedStatement.setDate(7, new java.sql.Date(transaction.getDateCompleted().getTime()));
            preparedStatement.setBoolean(8, transaction.isRequireUserApproval());
            preparedStatement.setBoolean(9, transaction.isPending());
            preparedStatement.setString(10, transaction.getMemo());
            preparedStatement.setInt(11, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
