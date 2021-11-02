package com.mitchell.ian.UI.Menus.Admin;

import com.mitchell.ian.UI.Menus.Admin.Options.EmployeeCreateOption;
import com.mitchell.ian.UI.Menus.Admin.Options.EmployeeDeleteOption;
import com.mitchell.ian.UI.Menus.Admin.Options.ListEmployeesOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class AdminEmployeeManagementMenu {
    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection;

            System.out.println("\nEmployee Management\n\n");

            System.out.println("1 - List Employees");
            System.out.println("2 - Add Employee");
            System.out.println("3 - Delete Employee");
            System.out.println("4 - Back");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> ListEmployeesOption.execute();
                case 2 -> EmployeeCreateOption.execute();
                case 3 -> EmployeeDeleteOption.execute();
                case 4 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
    }
}
