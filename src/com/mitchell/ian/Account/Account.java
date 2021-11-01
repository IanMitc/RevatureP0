package com.mitchell.ian.Account;

import com.mitchell.ian.Account.Exceptions.AccountApprovalPendingException;
import com.mitchell.ian.Account.Exceptions.CreditLockedException;
import com.mitchell.ian.Account.Exceptions.DebitLockedException;
import com.mitchell.ian.Account.Exceptions.InsufficientFundsException;
import com.mitchell.ian.Transaction.Transaction;

import java.util.List;

public class Account {
    int id;
    double balance;
    boolean creditLocked;
    boolean debitLocked;
    boolean pending;

    public Account(int id, double balance, boolean creditLocked, boolean debitLocked, boolean pending) {
        this.id = id;
        this.balance = balance;
        this.creditLocked = creditLocked;
        this.debitLocked = debitLocked;
        this.pending = pending;
    }

    public Account(double balance) {
        this.balance = balance;
        this.creditLocked = false;
        this.debitLocked = true;
        this.pending = true;
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

    public double getBalanceWithPending(){
        return 0.00;
    }

    public void credit(double amount) throws CreditLockedException, AccountApprovalPendingException{
        if (pending) {
            throw new AccountApprovalPendingException();
        }
        if (creditLocked){
            throw new CreditLockedException();
        }

        balance += amount;
    }

    public void debit(double amount) throws InsufficientFundsException, DebitLockedException, AccountApprovalPendingException{
        if (pending) {
            throw new AccountApprovalPendingException();
        }
        if (creditLocked){
            throw new DebitLockedException();
        }

        if (balance < amount){
            throw new InsufficientFundsException();
        }

        balance -= amount;
    }

    public boolean isDebitLocked(){
        return debitLocked;
    }

    public void setDebitLocked(boolean debitLocked) {
        this.debitLocked = debitLocked;
    }

    public List<Transaction> getOwners(){
        return null;
    }

    public List<Transaction> getLedger(){
        return null;
    }

    public List<Transaction> getPendingTransactions(){
        return null;
    }
}