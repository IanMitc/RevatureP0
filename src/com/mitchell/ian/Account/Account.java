package com.mitchell.ian.Account;

import com.mitchell.ian.Account.Exceptions.*;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.TransactionDao;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.User.User;

import java.util.List;

public class Account {
    private int id;
    private double balance;
    private boolean creditLocked;
    private boolean debitLocked;
    private boolean pending;
    private boolean closed;

    public Account(int id, double balance, boolean creditLocked, boolean debitLocked, boolean pending) {
        this.id = id;
        this.balance = balance;
        this.creditLocked = creditLocked;
        this.debitLocked = debitLocked;
        this.pending = pending;
        this.closed = false;
    }

    public Account(double balance) {
        this.balance = balance;
        this.creditLocked = false;
        this.debitLocked = true;
        this.pending = true;
        this.closed = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account otherAccount) {
            return otherAccount.getId() == this.getId();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Account Number: %o \n\tBalance: %.2f\n\tAvailable: %.2f", this.id, this.balance, this.getBalanceWithPending());
    }

    public boolean isCreditLocked() {
        return creditLocked;
    }

    public void setCreditLocked(boolean creditLocked) {
        this.creditLocked = creditLocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public double getBalance() {
        return balance;
    }

    public double getBalanceWithPending() {
        double pendingBalance = balance;

        List<Transaction> transactions = getPendingTransactions();

        for (Transaction t : transactions) {
            if (t.getFromAccount().equals(this)) {
                pendingBalance -= t.getAmount();
            }
        }
        return pendingBalance;
    }

    public void credit(double amount) throws NegativeAmountException, CreditLockedException, AccountApprovalPendingException {
        if (amount <= 0) {
            throw new NegativeAmountException();
        }
        if (pending) {
            throw new AccountApprovalPendingException();
        }
        if (creditLocked) {
            throw new CreditLockedException();
        }

        balance += amount;
    }

    public void debit(double amount) throws NegativeAmountException, InsufficientFundsException, DebitLockedException, AccountApprovalPendingException {
        if (amount <= 0) {
            throw new NegativeAmountException();
        }
        if (pending) {
            throw new AccountApprovalPendingException();
        }
        if (creditLocked) {
            throw new DebitLockedException();
        }

        if (this.getBalanceWithPending() < amount) {
            throw new InsufficientFundsException();
        }

        balance -= amount;
    }

    public boolean isDebitLocked() {
        return debitLocked;
    }

    public void setDebitLocked(boolean debitLocked) {
        this.debitLocked = debitLocked;
    }

    public List<User> getOwners() {
        return null;
    }

    public List<Transaction> getLedger() {
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        return transactionDao.getAllTransactions(this);
    }


    public List<Transaction> getPendingTransactions() {
        TransactionDao transactionDao = DaoFactory.getTransactionDao();
        return transactionDao.getAllPendingTransactions(this);
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}