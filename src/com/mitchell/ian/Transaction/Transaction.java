package com.mitchell.ian.Transaction;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Transaction.Exceptions.CompletedTransactionException;
import com.mitchell.ian.Transaction.Exceptions.InvalidAmountException;
import com.mitchell.ian.User.User;

import java.util.Date;

public class Transaction {
    private final double amount;
    private final Account fromAccount;
    private final Account toAccount;
    private final String memo;
    private final User initiatedBy;
    private final Date dateInitiated;
    private final boolean requireUserApproval;
    private int id;
    private User completedBy;
    private Date dateCompleted;
    private boolean pending;
    private boolean canceled;
    public Transaction(double amount, Account fromAccount, Account toAccount, String memo, User initiatedBy, boolean requireUserApproval) throws InvalidAmountException, NullPointerException {
        if (amount <= 0)
            this.amount = amount;
        else
            throw new InvalidAmountException();

        if (fromAccount != null && toAccount != null) {
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
        } else
            throw new NullPointerException("Transaction attempted with Null account");

        if (initiatedBy != null)
            this.initiatedBy = initiatedBy;
        else
            throw new NullPointerException("Transaction attempted by invalid User");

        this.memo = memo;
        dateInitiated = new Date();
        pending = true;
        canceled = false;
        this.requireUserApproval = requireUserApproval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateInitiated() {
        return dateInitiated;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public boolean isPending() {
        return pending;
    }

    public double getAmount() {
        return amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public String getMemo() {
        return memo;
    }

    public User getInitiatedBy() {
        return initiatedBy;
    }

    public void cancelTransaction(User user) throws CompletedTransactionException, NullPointerException {
        if (pending) {
            pending = false;
            canceled = true;
            dateCompleted = new Date();
        } else
            throw new CompletedTransactionException();

        if (user != null)
            completedBy = user;
        else
            throw new NullPointerException("Transaction attempted by invalid User");
    }

    public void approveTransaction(User user) throws NullPointerException {
        pending = false;
        dateCompleted = new Date();

        if (user != null)
            completedBy = user;
        else
            throw new NullPointerException("Transaction attempted by invalid User");
    }

    public boolean isRequireUserApproval() {
        return requireUserApproval;
    }
}