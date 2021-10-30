package com.mitchell.ian.User;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Permissions.Permissions;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private final Permissions.Role userRole;

    public User(String name, String email, String password, Permissions.Role userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String attemptedPassword) {
        return password.equals(attemptedPassword);
    }

    public boolean changePassword(String currentPassword, String newPassword) {
        if (checkPassword(currentPassword)) {
            password = newPassword;
            return true;
        } else
            return false;
    }

    public Permissions.Role getRole() {
        return userRole;
    }
}
