package com.mitchell.ian.User;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.User2AccountDao;
import com.mitchell.ian.Permissions.Permissions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User {

    public Customer(String name, String email, String password) {
        super(name, email, password, Permissions.Role.CUSTOMER);
    }

    public Customer(int id, String name, String email, String password) {
        super(id, name, email, Permissions.Role.CUSTOMER, password);
    }

    public List<Account> getAccounts() {
        AccountDao accountDao = DaoFactory.getAccountDao();

        return accountDao.getAllAccounts(this);
    }

    public List<Account> addAccount(Account account) {
        User2AccountDao user2AccountDao = DaoFactory.getUser2AccountDao();
        AccountDao accountDao = DaoFactory.getAccountDao();
        user2AccountDao.connect(this.getId(), account.getId());

        return accountDao.getAllAccounts(this);
    }

    public List<Account> addAccount(Integer id) {
        User2AccountDao user2AccountDao = DaoFactory.getUser2AccountDao();
        AccountDao accountDao = DaoFactory.getAccountDao();
        user2AccountDao.connect(this.getId(), id);

        return accountDao.getAllAccounts(this);
    }

    public List<Account> removeAccount(Account account) {
        User2AccountDao user2AccountDao = DaoFactory.getUser2AccountDao();
        AccountDao accountDao = DaoFactory.getAccountDao();
        user2AccountDao.disconnect(this.getId(), account.getId());

        return accountDao.getAllAccounts(this);
    }

    public List<Account> removeAccount(Integer id) {
        User2AccountDao user2AccountDao = DaoFactory.getUser2AccountDao();
        AccountDao accountDao = DaoFactory.getAccountDao();
        user2AccountDao.disconnect(this.getId(), id);

        return accountDao.getAllAccounts(this);
    }

    public void addAccounts(List<Account> accounts) {
        for (Account a : accounts){
            addAccount(a.getId());
        }
    }
}
