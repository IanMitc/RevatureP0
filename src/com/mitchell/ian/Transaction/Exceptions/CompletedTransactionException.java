package com.mitchell.ian.Transaction.Exceptions;

public class CompletedTransactionException extends Exception {
    public CompletedTransactionException() {
        super("Unable to change a completed transaction.");
    }
}
