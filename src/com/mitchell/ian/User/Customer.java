package com.mitchell.ian.User;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Permissions.Permissions;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Integer> accountList;

    public Customer(String name, String email, String password) {
        super(name, email, password, Permissions.Role.CUSTOMER);
        accountList = new ArrayList<>();
    }

    public Customer(int id, String name, String email, String password) {
        super(id, name, email, Permissions.Role.CUSTOMER, password);
        accountList = new ArrayList<>();
    }

    public List<Integer> getAccounts() {
        return accountList;
    }

    public List<Integer> addAccount(Account account) {
        accountList.add(account.getId());
        return accountList;
    }

    public List<Integer> addAccount(Integer id) {
        accountList.add(id);
        return accountList;
    }

    public List<Integer> removeAccount(Account account) {
        accountList.remove(account.getId());
        return accountList;
    }

    public List<Integer> removeAccount(Integer id) {
        accountList.remove(id);
        return accountList;
    }

    public void addAccounts(List<Integer> accounts) {
        for (int i : accounts){
            addAccount(i);
        }
    }
}
