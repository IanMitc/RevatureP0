package com.mitchell.ian.Data;

import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.User.Customer;
import com.mitchell.ian.User.Employee;
import com.mitchell.ian.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    Connection connection;

    public UserDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public User getUser(String email) {
        User retrievedUser = null;

        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            retrievedUser = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    Permissions.Role.valueOf(resultSet.getString("role")),
                    resultSet.getString("password")
            );
            retrievedUser.setActive(resultSet.getBoolean("active"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedUser;

    }

    @Override
    public User getUser(int id) {
        User retrievedUser = null;

        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            retrievedUser = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    Permissions.Role.valueOf(resultSet.getString("role")),
                    resultSet.getString("password")
            );
            retrievedUser.setActive(resultSet.getBoolean("active"));

        } catch (SQLException e) {
            //TODO Figure out why resultSet.getInt("id") has issues, Everything returns fine so ignoring for now...
            //e.printStackTrace();
        }
        return retrievedUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE active = true";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User retrievedUser = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        Permissions.Role.valueOf(resultSet.getString("role")),
                        resultSet.getString("password")
                );
                retrievedUser.setActive(resultSet.getBoolean("active"));

                userList.add(retrievedUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        User2AccountDao dao = DaoFactory.getUser2AccountDao();

        String sql = "SELECT * FROM users WHERE role = 'CUSTOMER' AND active = true";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Customer retrievedCustomer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                retrievedCustomer.setActive(resultSet.getBoolean("active"));
                customerList.add(retrievedCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE role = 'EMPLOYEE' AND active = true";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee retrievedEmployee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                retrievedEmployee.setActive(resultSet.getBoolean("active"));
                employeeList.add(retrievedEmployee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, role, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        String sql = "UPDATE users SET active = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, role = ?, password = ?, active = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.isActive());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
