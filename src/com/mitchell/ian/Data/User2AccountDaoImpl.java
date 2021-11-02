package com.mitchell.ian.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User2AccountDaoImpl implements User2AccountDao {
    Connection connection;

    public User2AccountDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<Integer> getAccounts(int userId) {
        List<Integer> accountList = new ArrayList<>();

        String sql = "SELECT * FROM user2account WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                accountList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public List<Integer> getUsers(int accountId) {
        List<Integer> userList = new ArrayList<>();

        String sql = "SELECT * FROM user2account WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                userList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void connect(int userId, int accountId) {
        String sql = "INSERT INTO user2accounts (user_id, account_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, userId);
            preparedStatement.setDouble(2, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect(int userId, int accountId) {
        String sql = "DELETE FROM user2account WHERE user_id = ? AND account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
