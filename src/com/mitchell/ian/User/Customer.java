package com.mitchell.ian.User;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Account> accountList;

    public Customer(String name, String email, String password) {
        super(name, email, password, Permissions.Role.CUSTOMER);
        accountList = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accountList;
    }

    public List<Account> addAccount(Account account) {
        accountList.add(account);
        return accountList;
    }

    public List<Account> removeAccount(Account account) {
        accountList.remove(account);
        return accountList;
    }
}
