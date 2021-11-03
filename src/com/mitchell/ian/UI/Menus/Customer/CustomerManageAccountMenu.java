package com.mitchell.ian.UI.Menus.Customer;

import com.mitchell.ian.UI.Menus.Customer.Options.ApplyForNewAccountOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class CustomerManageAccountMenu {
    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection;

            System.out.println("\nManage Accounts\n\n");

            System.out.println("1 - Approve Pending Transfers");
            System.out.println("2 - Apply for New Account");
            System.out.println("3 - Back");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> ApprovePendingMenu.show();
                case 2 -> ApplyForNewAccountOption.execute();
                case 3 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
        Clear.console();
    }
}
