package com.mitchell.ian.UI.Menus.Shared.Options;

import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.UI.Menus.Admin.AdminMenu;
import com.mitchell.ian.UI.Menus.Customer.CustomerMenu;
import com.mitchell.ian.UI.Menus.Employee.EmployeeMenu;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class LoginOption {
    public static void execute() {
        Permissions permissions = Permissions.getPermissions();

        Clear.console();
        System.out.println("Please Login");
        String email = Ask.forString("Email");
        String password = Ask.forString("Password");

        boolean loginSuccessful = permissions.login(email, password);

        if (loginSuccessful) {
            System.out.println("Logged In");
        } else {
            System.out.println("Login Failed");
            return;
        }

        switch (permissions.getUserRole()) {
            case EMPLOYEE -> EmployeeMenu.show();
            case CUSTOMER -> CustomerMenu.show();
            case ADMIN -> AdminMenu.show();
            default -> {
                System.out.println("Invalid Permissions");
                permissions.logout();
            }
        }
    }
}
