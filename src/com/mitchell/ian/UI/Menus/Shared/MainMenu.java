package com.mitchell.ian.UI.Menus.Shared;

import com.mitchell.ian.UI.Menus.Customer.Options.CustomerRegistrationOption;
import com.mitchell.ian.UI.Menus.Shared.Options.ExitOption;
import com.mitchell.ian.UI.Menus.Shared.Options.LoginOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class MainMenu {

    public static void show() {
        boolean run = true;
        while (run) {
            int selection = 0;
            Clear.console();

            System.out.println("\nWelcome to The Bank\n\n");

            System.out.println("1 - Login");
            System.out.println("2 - Register as New Customer");
            System.out.println("3 - Exit");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
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
