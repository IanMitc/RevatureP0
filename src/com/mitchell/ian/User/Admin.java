package com.mitchell.ian.User;

import com.mitchell.ian.Permissions.Permissions;

public class Admin extends User {

    public Admin(String name, String email, String password) {
        super(name, email, password, Permissions.Role.ADMIN);
    }
}
