package com.mitchell.ian.Account.Exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("There are not enough funds available to perform this transaction.");
    }
}
