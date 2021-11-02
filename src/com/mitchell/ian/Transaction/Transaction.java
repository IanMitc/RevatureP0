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

    public Transaction(double amount, Account fromAccount, Account toAccount, String memo, User initiatedBy, Date dateInitiated, boolean requireUserApproval, int id, User completedBy, Date dateCompleted, boolean pending) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.memo = memo;
        this.initiatedBy = initiatedBy;
        this.dateInitiated = dateInitiated;
        this.requireUserApproval = requireUserApproval;
        this.id = id;
        this.completedBy = completedBy;
        this.dateCompleted = dateCompleted;
        this.pending = pending;
        this.canceled = false;
    }

    public Transaction(double amount, Account fromAccount, Account toAccount, String memo, User initiatedBy, boolean requireUserApproval) throws InvalidAmountException, NullPointerException {
        if (amount >= 0.00)
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
        dateCompleted = null;
        completedBy = null;
        dateInitiated = new Date();
        pending = true;
        canceled = false;
        this.requireUserApproval = requireUserApproval;
    }

    public User getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(User completedBy) {
        this.completedBy = completedBy;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
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