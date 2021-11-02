package com.mitchell.ian.UI.Menus.Customer.Options;

import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.UserDao;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.Customer;

public class CustomerRegistrationOption {
    public static void execute() {
        Clear.console();

        String name = Ask.forString("What is your name");
        String email = Ask.forString("What is your email");
        String password = Ask.forString("Enter Password");

        Customer customer = new Customer(name, email, password);

        UserDao userDao = DaoFactory.getUserDao();
        userDao.addUser(customer);

        Clear.console();
        System.out.println(customer + "\nCreated");
    }
}
