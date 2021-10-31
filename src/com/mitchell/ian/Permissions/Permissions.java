package com.mitchell.ian.Permissions;

import com.mitchell.ian.Data.UserDao;
import com.mitchell.ian.User.User;

public class Permissions {
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

    public enum Role {
        SYSTEM,
        ADMIN,
        EMPLOYEE,
        CUSTOMER
    }
}
