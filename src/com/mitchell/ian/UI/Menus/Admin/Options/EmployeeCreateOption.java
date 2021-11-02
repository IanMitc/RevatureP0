package com.mitchell.ian.UI.Menus.Admin.Options;

import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.UserDao;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.Employee;

public class EmployeeCreateOption {
    public static void execute() {
        Clear.console();

        String name = Ask.forString("Name");
        String email = Ask.forString("Email");
        String password = Ask.forString("Password");

        Employee employee = new Employee(name, email, password);

        UserDao userDao = DaoFactory.getUserDao();
        userDao.addUser(employee);

        Clear.console();
        System.out.println(employee.toString() + "\nCreated");
    }
}
