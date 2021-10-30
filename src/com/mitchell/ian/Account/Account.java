package com.mitchell.ian.Account;

import com.mitchell.ian.Account.Exceptions.*;
import com.mitchell.ian.Transaction.Transaction;
import com.mitchell.ian.User.User;

import java.util.List;

abstract class Account {
    abstract double getBalance();
    abstract void credit(double amount) throws CreditLockedException, AccountApprovalPendingException;
    abstract void debit(double amount) throws InsufficientFundsException, DebitLockedException, AccountApprovalPendingException;
    abstract boolean isCreditLocked();
    abstract boolean isDebitLocked();
    abstract boolean isPendingApproval();
    abstract List<User> getOwners();
    abstract List<Transaction> getLedger();
}
