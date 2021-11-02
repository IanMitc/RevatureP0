package com.mitchell.ian.Data;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.User.User;

import java.util.List;

public interface TransactionDao {
    Transaction getTransaction(int id);

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactions(User user);

    List<Transaction> getAllTransactions(Account account);

    List<Transaction> getAllPendingTransactions();

    List<Transaction> getAllPendingTransactions(User user);

    List<Transaction> getAllPendingTransactions(Account account);

    void addTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);
}
