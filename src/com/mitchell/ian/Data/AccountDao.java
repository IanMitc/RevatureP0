package com.mitchell.ian.Data;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.User.User;

import java.util.List;

public interface AccountDao {
    Account getAccount(int id);

    List<Account> getAllAccounts();

    List<Account> getAllAccounts(User user);

    List<Account> getAllPendingAccounts();

    void addAccount(Account account);

    void deleteAccount(Account account);

    void updateAccount(Account account);
}
