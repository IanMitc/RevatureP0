package com.mitchell.ian.Data;

import com.mitchell.ian.User.User;

import java.util.List;

public interface UserDao {
    User getUser(String email);
    User getUser(int id);
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
}
