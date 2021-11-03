package com.mitchell.ian.UI.Menus.Employee.Options;

import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.TransactionDao;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.UI.Utility.Clear;

import java.util.List;

public class ViewTransactionsOption {
    public static void execute() {
        Clear.console();
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        List<Transaction> transactions = transactionDao.getAllTransactions();
        for (Transaction t : transactions)
            System.out.println(t + "\n");
    }
}
