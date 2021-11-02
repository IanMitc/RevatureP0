package com.mitchell.ian.User;

import com.mitchell.ian.Permissions.Permissions;

public class User {
    private final Permissions.Role userRole;
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean active;

    public User(int id, String name, String email, Permissions.Role userRole, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
        this.password = password;
        this.active = true;
    }

    public User(String name, String email, String password, Permissions.Role userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.active = true;
    }

    public String getPassword() {
        return password;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " Name: " + this.name + " Email: " + this.email;
    }
}
