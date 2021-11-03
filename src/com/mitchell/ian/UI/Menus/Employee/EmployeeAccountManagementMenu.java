package com.mitchell.ian.UI.Menus.Employee;

import com.mitchell.ian.UI.Menus.Employee.Options.ViewCustomerAccountsOption;
import com.mitchell.ian.UI.Menus.Shared.Options.ExitOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class EmployeeAccountManagementMenu {    public static void show() {
    Clear.console();

    boolean run = true;
    while (run) {
        int selection;

        System.out.println("\nTitle\n\n");

        System.out.println("1 - View Customer Accounts");
        System.out.println("2 - Approve Pending Accounts");
        System.out.println("4 - Back");

        try {
            selection = Ask.forInt("Select Option");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            continue;
        }

        switch (selection) {
            case 1 -> ViewCustomerAccountsOption.execute();
            case 2 -> ApprovePendingAccountMenu.show();
            case 3 -> run = false;
            default -> System.out.println("\nPlease make a valid selection.\n");
        }
    }
    Clear.console();
}
}
