package com.mitchell.ian.User;

import com.mitchell.ian.Permissions.Permissions;

public class Employee extends User {

    public Employee(String name, String email, String password) {
        super(name, email, password, Permissions.Role.EMPLOYEE);
    }

    public Employee(int id, String name, String email, String password) {
        super(id, name, email, Permissions.Role.EMPLOYEE, password);
    }
}
