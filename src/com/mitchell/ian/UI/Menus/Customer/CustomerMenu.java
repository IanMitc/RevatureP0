package com.mitchell.ian.UI.Menus.Customer;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.UI.Menus.Customer.Options.DepositOption;
import com.mitchell.ian.UI.Menus.Customer.Options.TransferOption;
import com.mitchell.ian.UI.Menus.Customer.Options.WithdrawOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

import java.util.List;

public class CustomerMenu {
    public static void show() {
        Permissions permissions = Permissions.getPermissions();
        User user = permissions.getUser();
        AccountDao accountDao = DaoFactory.getAccountDao();

        Clear.console();

        boolean run = true;
        while (run) {
            int selection;
            List<Account> accounts = accountDao.getAllAccounts(user);

            System.out.println("\nWelcome, " + user.getName() + ".\n");
            for (Account a : accounts)
                System.out.println(a + "\n");

            System.out.println();
            System.out.println("1 - Manage Accounts");
            System.out.println("2 - Transfer");
            System.out.println("3 - Withdraw");
            System.out.println("4 - Deposit");
            System.out.println("5 - Logout");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> CustomerManageAccountMenu.show();
                case 2 -> TransferOption.execute();
                case 3 -> WithdrawOption.execute();
                case 4 -> DepositOption.execute();
                case 5 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
    }
}