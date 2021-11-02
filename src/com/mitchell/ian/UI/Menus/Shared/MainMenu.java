package com.mitchell.ian.UI.Menus.Shared;

import com.mitchell.ian.UI.Menus.Shared.Options.LoginOption;
import com.mitchell.ian.UI.Menus.Shared.Options.LogoutOption;
import com.mitchell.ian.UI.Menus.Shared.Options.RegisterOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

public class MainMenu {

    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection = 0;
            System.out.println("Welcome to The Bank\n\n");

            System.out.println("1 - Login");
            System.out.println("2 - Register as New Customer");
            System.out.println("3 - Exit");
            try {
                selection = Ask.forInt("What can we do for you today");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (selection) {
                case 1:
                    LoginOption.execute();
                    break;
                case 2:
                    RegisterOption.execute();
                    break;
                case 3:
                    run = false;
                    LogoutOption.execute();
                    break;
                default:
                    System.out.println("\nPlease make a valid selection.\n");
                    break;
            }
        }
    }
}
