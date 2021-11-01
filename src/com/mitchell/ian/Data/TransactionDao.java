package com.mitchell.ian.Data;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.User.User;

import java.util.List;

public interface TransactionDao {
    Transaction getTransaction(User user);

    Transaction getTransaction(Account account);

    Transaction getTransaction(int id);

    List<Transaction> getAllTransactions();

    List<Transaction> getAllPendingTransactions();

    List<Transaction> getAllPendingTransactions(User user);

    List<Transaction> getAllPendingTransactions(Account account);

    void addTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);
}
