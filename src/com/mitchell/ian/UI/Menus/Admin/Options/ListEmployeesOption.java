package com.mitchell.ian.UI.Menus.Admin.Options;

import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.UserDao;
import com.mitchell.ian.User.Employee;

import java.util.List;

public class ListEmployeesOption {
    public static void execute() {
        UserDao userDao = DaoFactory.getUserDao();

        List<Employee> employeeList = userDao.getAllEmployees();

        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }
}
