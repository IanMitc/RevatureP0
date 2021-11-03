package com.mitchell.ian.UI.Menus.Customer.Options;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.TransactionDao;
import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

import java.util.List;

public class WithdrawOption {
    public static void execute() {
        Clear.console();
        Permissions permissions = Permissions.getPermissions();
        User user = permissions.getUser();
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> accounts = accountDao.getAllAccounts(user);

        for (Account a : accounts)
            System.out.println(a + "\n");

        int fromId = 0;
        boolean run = true;
        while (run) {
            try {
                fromId = Ask.forInt("Enter the account number to withdraw from (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (fromId == 0) {
            Clear.console();
            System.out.println("Withdrawal Canceled");
            return;
        }

        double amount = 0;
        run = true;
        while (run) {
            try {
                amount = Ask.forDouble("Enter the amount to withdraw (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (amount == 0.00) {
            Clear.console();
            System.out.println("Withdrawal Canceled");
            return;
        }

        Account fromAccount = accountDao.getAccount(fromId);

        if (!accounts.contains(fromAccount)) {
            Clear.console();
            System.out.println("You don't have permissions to withdraw from this account! Withdrawal Cancelled.");
            return;
        }

        try {
            fromAccount.debit(amount);
            accountDao.updateAccount(fromAccount);
        } catch (Exception e) {
            Clear.console();
            System.out.println(e.getMessage());
        }

        Clear.console();
    }
}