package com.mitchell.ian.Data;

import com.mitchell.ian.User.Customer;
import com.mitchell.ian.User.Employee;
import com.mitchell.ian.User.User;

import java.util.List;

public interface UserDao {
    User getUser(String email);

    User getUser(int id);

    List<User> getAllUsers();

    List<Customer> getAllCustomers();

    List<Employee> getAllEmployees();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
