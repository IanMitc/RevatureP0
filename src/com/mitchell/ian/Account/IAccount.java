package com.mitchell.ian.Account;

import com.mitchell.ian.Account.Exceptions.*;

public interface IAccount {
    double getBalance();
    void credit(double amount) throws CreditLockedException, AccountApprovalPendingException;
    void debit(double amount) throws InsufficientFundsException, DebitLockedException, AccountApprovalPendingException;
    boolean isCreditLocked();
    boolean isDebitLocked();
    boolean isPendingApproval();
    List<User> getOwners();
    List<Transaction> getLedger();
}
