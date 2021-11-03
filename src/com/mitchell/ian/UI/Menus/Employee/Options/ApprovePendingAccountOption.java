package com.mitchell.ian.UI.Menus.Employee.Options;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;

import java.util.ArrayList;
import java.util.List;

public class ApprovePendingAccountOption {
    public static void execute() {
        Clear.console();
        AccountDao accountDao = DaoFactory.getAccountDao();

        List<Account> pendingAccounts = new ArrayList<>(accountDao.getAllPendingAccounts());

        for (Account a : pendingAccounts)
            System.out.println(a + "\n");


        int accountId = 0;
        boolean run = true;
        while (run) {
            try {
                accountId = Ask.forInt("Enter the account id to approve (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (accountId == 0) {
            Clear.console();
            System.out.println("Approval Canceled");
            return;
        }

        Account account = accountDao.getAccount(accountId);
        try {
            if (pendingAccounts.contains(account)) {
                account.setPending(false);
                accountDao.updateAccount(account);
            } else {
                Clear.console();
                System.out.println("Please choose a valid account");
                return;
            }
        } catch (Exception e) {
            Clear.console();
            System.out.println(e.getMessage());
            return;
        }

        Clear.console();
    }
}
