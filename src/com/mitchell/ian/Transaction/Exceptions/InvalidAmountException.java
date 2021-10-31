package com.mitchell.ian.Transaction.Exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("Transaction amounts must be greater than 0.");
    }
}
