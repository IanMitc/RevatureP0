package com.mitchell.ian.UI.Menus.Employee;

import com.mitchell.ian.UI.Menus.Employee.Options.ViewTransactionsOption;
import com.mitchell.ian.UI.Menus.Shared.Options.ExitOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class EmployeeMenu {
    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection;

            System.out.println("\nEmployee Menu\n\n");

            System.out.println("1 - Account Management");
            System.out.println("2 - View Transactions");
            System.out.println("3 - Logout");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> EmployeeAccountManagementMenu.show();
                case 2 -> ViewTransactionsOption.execute();
                case 3 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
        Clear.console();
    }
}
