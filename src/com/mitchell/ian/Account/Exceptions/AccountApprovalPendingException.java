package com.mitchell.ian.Account.Exceptions;

public class AccountApprovalPendingException extends Exception {
    public AccountApprovalPendingException() {
        super("The account is locked pending approval.");
    }
}
