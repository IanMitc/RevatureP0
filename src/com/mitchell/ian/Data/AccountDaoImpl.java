package com.mitchell.ian.Data;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    Connection connection;

    public AccountDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Account getAccount(int id) {
        Account retrievedAccount = null;

        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            retrievedAccount = new Account(
                    resultSet.getInt("id"),
                    resultSet.getDouble("balance"),
                    resultSet.getBoolean("credit_locked"),
                    resultSet.getBoolean("debit_locked"),
                    resultSet.getBoolean("pending")
            );
            retrievedAccount.setClosed(resultSet.getBoolean("closed"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedAccount;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();

        String sql = "SELECT * FROM accounts WHERE closed = false";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Account retrievedAccount = new Account(
                        resultSet.getInt("id"),
                        resultSet.getDouble("balance"),
                        resultSet.getBoolean("credit_locked"),
                        resultSet.getBoolean("debit_locked"),
                        resultSet.getBoolean("pending")
                );
                retrievedAccount.setClosed(resultSet.getBoolean("closed"));

                accountList.add(retrievedAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public List<Account> getAllAccounts(User user) {
        List<Account> accountList = new ArrayList<>();

        String sql = "SELECT * FROM user2account WHERE user_id = ? AND closed = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account retrievedAccount = getAccount(resultSet.getInt("account_id"));
                accountList.add(retrievedAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public List<Account> getAllPendingAccounts() {
        List<Account> accountList = new ArrayList<>();

        String sql = "SELECT * FROM accounts WHERE pending = true AND closed = false";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Account retrievedAccount = new Account(
                        resultSet.getInt("id"),
                        resultSet.getDouble("balance"),
                        resultSet.getBoolean("credit_locked"),
                        resultSet.getBoolean("debit_locked"),
                        resultSet.getBoolean("pending")
                );
                accountList.add(retrievedAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public void addAccount(Account account) {
        String sql = "INSERT INTO accounts (balance, pending) VALUES (?, true)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            account.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Account account) {
        String sql = "UPDATE accounts SET closed = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "UPDATE accounts SET balance = ?, credit_locked = ?, debit_locked = ?, pending = ?, closed = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setBoolean(2, account.isCreditLocked());
            preparedStatement.setBoolean(3, account.isDebitLocked());
            preparedStatement.setBoolean(4, account.isPending());
            preparedStatement.setBoolean(5, account.isClosed());
            preparedStatement.setInt(6, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
