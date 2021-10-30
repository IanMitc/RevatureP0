package com.mitchell.ian.User;

import com.mitchell.ian.Account.Permissions;

public abstract class User {
    private String name;
    private String email;
    private String password;
    private Permissions.Role userRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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
