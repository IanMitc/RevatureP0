package com.mitchell.ian.UI.Menus.Customer;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.TransactionDao;
import com.mitchell.ian.Permissions.Permissions;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.UI.Menus.Customer.Options.ApprovePendingTransactionOption;
import com.mitchell.ian.UI.Menus.Customer.Options.DenyPendingTransactionOption;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

import java.util.ArrayList;
import java.util.List;

public class ApprovePendingMenu {
    public static void show() {
        Clear.console();

        boolean run = true;
        while (run) {
            int selection;
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

            System.out.println("1 - Approve Transaction");
            System.out.println("2 - Deny Transaction");
            System.out.println("3 - Back");
            try {
                selection = Ask.forInt("Select Option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (selection) {
                case 1 -> ApprovePendingTransactionOption.execute();
                case 2 -> DenyPendingTransactionOption.execute();
                case 3 -> run = false;
                default -> System.out.println("\nPlease make a valid selection.\n");
            }
        }
        Clear.console();
    }
}