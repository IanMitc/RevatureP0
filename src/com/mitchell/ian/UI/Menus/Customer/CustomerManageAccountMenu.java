package com.mitchell.ian.UI.Menus.Customer;

import com.mitchell.ian.UI.Menus.Shared.Options.ExitOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class CustomerManageAccountMenu {
    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection;

            System.out.println("\nTitle\n\n");

            System.out.println("1 - ");
            System.out.println("2 - ");
            System.out.println("3 - ");
            System.out.println("4 - Back");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> ExitOption.execute();
                case 2 -> ExitOption.execute();
                case 3 -> ExitOption.execute();
                case 4 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
    }
}
