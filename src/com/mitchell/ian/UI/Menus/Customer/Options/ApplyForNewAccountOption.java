package com.mitchell.ian.UI.Menus.Customer.Options;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.User2AccountDao;
import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

public class ApplyForNewAccountOption {
    public static void execute() {
        Clear.console();
        User user = Permissions.getPermissions().getUser();

        System.out.println("\nApply for new account\n\n");
        double amount = 0;
        boolean run = true;
        while (run) {
            try {
                amount = Ask.forDouble("Enter the amount to deposit (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (amount == 0.00) {
            Clear.console();
            System.out.println("Deposit Canceled");
            return;
        }

        Account account = new Account(amount);
        AccountDao accountDao = DaoFactory.getAccountDao();
        User2AccountDao user2AccountDao = DaoFactory.getUser2AccountDao();

        accountDao.addAccount(account);
        user2AccountDao.connect(user.getId(), account.getId());

        Clear.console();
        System.out.println("" + user.getId() + account.getId());

        System.out.println("The account will be available after approval.");
    }
}
