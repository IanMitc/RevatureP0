package com.mitchell.ian.UI.Menus.Employee;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.UI.Menus.Employee.Options.ApprovePendingAccountOption;
import com.mitchell.ian.UI.Menus.Employee.Options.DenyPendingAccountOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

import java.util.ArrayList;
import java.util.List;

public class ApprovePendingAccountMenu {
    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection;
            AccountDao accountDao = DaoFactory.getAccountDao();

            System.out.println("\nApprove Pending Accounts\n\n");

            List<Account> pendingAccounts = new ArrayList<>(accountDao.getAllPendingAccounts());

            for (Account a : pendingAccounts)
                System.out.println(a + "\n");

            System.out.println("1 - Approve Account");
            System.out.println("2 - Deny Account");
            System.out.println("3 - Back");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> ApprovePendingAccountOption.execute();
                case 2 -> DenyPendingAccountOption.execute();
                case 3 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
        Clear.console();
    }
}
