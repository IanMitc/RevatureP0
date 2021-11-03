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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprovePendingTransactionOption {
    public static void execute() {
        Clear.console();
        Permissions permissions = Permissions.getPermissions();
        User user = permissions.getUser();
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        AccountDao accountDao = DaoFactory.getAccountDao();

        System.out.println("\nApprove Pending Transactions\n\n");

        List<Transaction> pendingTransactions = new ArrayList<>();

        for (Account a : accountDao.getAllAccounts(user))
            pendingTransactions.addAll(transactionDao.getAllPendingTransactions(a));

        for (Transaction t : pendingTransactions)
            System.out.println(t + "\n");

        int transactionId = 0;
        boolean run = true;
        while (run) {
            try {
                transactionId = Ask.forInt("Enter the transfer id to approve (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (transactionId == 0) {
            Clear.console();
            System.out.println("Approval Canceled");
            return;
        }

        Transaction transaction = transactionDao.getTransaction(transactionId);
        try {
            if (pendingTransactions.contains(transaction)) {
                transaction.setPending(false);
                transaction.setCompletedBy(user);
                transaction.setDateCompleted(new Date());
                Account fromAccount = transaction.getFromAccount();
                fromAccount.debit(transaction.getAmount());
                accountDao.updateAccount(fromAccount);
                Account toAccount = transaction.getToAccount();
                toAccount.credit(transaction.getAmount());
                accountDao.updateAccount(toAccount);
                transactionDao.updateTransaction(transaction);
            } else {
                Clear.console();
                System.out.println("Please choose a valid transaction");
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
