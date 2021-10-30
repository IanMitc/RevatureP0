package com.mitchell.ian.Account.Exceptions;

public class InsufficientPermissionsException extends Exception {

    public InsufficientPermissionsException() {
        super("You do not have the appropriate permissions to perform this action.");
    }
}
