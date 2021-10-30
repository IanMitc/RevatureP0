package com.mitchell.ian.Transaction;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.User.User;

public class Transaction {
    private final double amount;
    private final Account fromAccount;
    private final Account toAccount;
    private final String memo;
    private final User initiatedBy;

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

    public Transaction(double amount, Account fromAccount, Account toAccount, String memo, User initiatedBy) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.memo = memo;
        this.initiatedBy = initiatedBy;
    }
}
