package com.mitchell.ian.Account;

import com.mitchell.ian.User.Data.UserDao;
import com.mitchell.ian.User.User;

public class Permissions {
    public enum Role {
        SYSTEM,
        ADMIN,
        EMPLOYEE,
        CUSTOMER
    }

    private static Permissions permissions;
    private static User user;
    private static boolean loggedIn;

    private Permissions() {
        user = null;
        loggedIn = false;
    }

    public static Permissions getPermissions() {
        if (permissions == null) {
            permissions = new Permissions();
        }

        return permissions;
    }

    public boolean login(UserDao dao, String username, String password) {
        user = dao.getUserByUsername();
        if (user.checkPassword(password)) {
            loggedIn = true;
            return true;
        } else {
            user = null;
            return false;
        }

    }

    public static void logout() {
        user = null;
        loggedIn = false;
    }

    public static User getUser() {
        return user;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static Role getUserRole() {
        return user.getRole();
    }
}
