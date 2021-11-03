package com.mitchell.ian.UI.Menus.Customer.Options;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.TransactionDao;
import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

import java.util.Date;
import java.util.List;

public class TransferOption {
    public static void execute() {
        Clear.console();
        Permissions permissions = Permissions.getPermissions();
        User user = permissions.getUser();
        AccountDao accountDao = DaoFactory.getAccountDao();
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        List<Account> accounts = accountDao.getAllAccounts(user);

        for (Account a : accounts)
            System.out.println(a + "\n");

        int fromId = 0;
        boolean run = true;
        while (run) {
            try {
                fromId = Ask.forInt("Enter the account number to transfer from (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (fromId == 0) {
            Clear.console();
            System.out.println("Transfer Canceled");
            return;
        }

        int toId = 0;
        run = true;
        while (run) {
            try {
                toId = Ask.forInt("Enter the account number to transfer to (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (toId == 0) {
            Clear.console();
            System.out.println("Transfer Canceled");
            return;
        }

        double amount = 0;
        run = true;
        while (run) {
            try {
                amount = Ask.forDouble("Enter the amount to transfer to (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (amount == 0.00) {
            Clear.console();
            System.out.println("Transfer Canceled");
            return;
        }
        String memo = Ask.forString("Enter memo");

        Account fromAccount = accountDao.getAccount(fromId);
        Account toAccount = accountDao.getAccount(toId);

        boolean ownAccount = false;
        try {
            Transaction transaction = new Transaction(amount, fromAccount, toAccount, memo, user, true);
            transactionDao.addTransaction(transaction);
            if (accounts.contains(fromAccount) && accounts.contains(toAccount)) {
                ownAccount = true;
                transaction.setPending(false);
                transaction.setCompletedBy(user);
                transaction.setDateCompleted(new Date());
                fromAccount.debit(amount);
                accountDao.updateAccount(fromAccount);
                toAccount.credit(amount);
                accountDao.updateAccount(toAccount);
            }
            transactionDao.updateTransaction(transaction);
        } catch (Exception e) {
            Clear.console();
            System.out.println(e.getMessage());
            return;
        }

        Clear.console();
        System.out.println(ownAccount ? "Transfer completed." : "Transfer Pending");
    }
}
