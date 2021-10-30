package com.mitchell.ian.Account.Exceptions;

public class DebitLockedException extends Exception {
    public DebitLockedException() {
        super("The ability to withdraw from this account has been locked.");
    }
}
