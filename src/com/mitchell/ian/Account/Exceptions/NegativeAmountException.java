package com.mitchell.ian.Account.Exceptions;

public class NegativeAmountException extends Exception {
    public NegativeAmountException() {
        super("All amounts must be positive numbers.");
    }
}
