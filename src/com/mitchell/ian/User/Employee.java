package com.mitchell.ian.User;

import com.mitchell.ian.Permissions.Permissions;

public class Employee extends User {

    public Employee(String name, String email, String password) {
        super(name, email, password, Permissions.Role.EMPLOYEE);
    }
}
