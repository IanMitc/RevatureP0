package com.mitchell.ian.Data;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.User.User;

import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public Transaction getTransaction(User user) {
        return null;
    }

    @Override
    public Transaction getTransaction(Account account) {
        return null;
    }

    @Override
    public Transaction getTransaction(int id) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getAllPendingTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getAllPendingTransactions(User user) {
        return null;
    }

    @Override
    public List<Transaction> getAllPendingTransactions(Account account) {
        return null;
    }

    @Override
    public void addTransaction(Transaction transaction) {

    }

    @Override
    public void deleteTransaction(Transaction transaction) {

    }

    @Override
    public void updateTransaction(Transaction transaction) {

    }
}
