package com.mitchell.ian.UI.Menus.Admin;

import com.mitchell.ian.UI.Menus.Customer.Options.CustomerRegistrationOption;
import com.mitchell.ian.UI.Menus.Shared.Options.ExitOption;
import com.mitchell.ian.UI.Menus.Shared.Options.LoginOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class AdminEmployeeManagementMenu {
    public static void show() {
        boolean run = true;
        while (run) {
            int selection = 0;
            Clear.console();

            System.out.println("\nTitle\n\n");

            System.out.println("1 - ");
            System.out.println("2 - ");
            System.out.println("3 - Back");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (selection) {
                case 1 -> LoginOption.execute();
                case 2 -> CustomerRegistrationOption.execute();
                case 3 -> {
                    run = false;
                    ExitOption.execute();
                }
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
    }
}
