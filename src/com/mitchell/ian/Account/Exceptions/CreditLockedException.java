package com.mitchell.ian.Account.Exceptions;

public class CreditLockedException extends Exception {
    public CreditLockedException() {
        super("The ability to deposit into this account has been locked");
    }
}
