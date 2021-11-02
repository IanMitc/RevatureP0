package com.mitchell.ian.UI.Menus.Admin;

import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.Admin;

public class AdminMenu {
    public static void show(){
        Permissions permissions = Permissions.getPermissions();

        if(permissions.getUserRole() != Permissions.Role.ADMIN){
            System.out.println("You don't have permission to view this page!");
            return;
        }

        Admin admin = (Admin) permissions.getUser();

        boolean run = true;
        while(run) {
            Clear.console();
            System.out.println("\nAdministrator Menu\n");
            System.out.println("1 - Manage Employees");
            System.out.println("2 - Logout");

            int selection = 0;
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (selection){
                case 1 -> AdminEmployeeManagementMenu.show();
                case 2 -> {
                    permissions.logout();
                    run = false;
                }
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
    }
}
