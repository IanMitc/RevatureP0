package com.mitchell.ian.UI.Menus.Employee.Options;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.UI.Utility.Clear;

import java.util.List;

public class ViewAllCustomerAccountsOption {
    public static void execute() {
        Clear.console();
        AccountDao accountDao = DaoFactory.getAccountDao();
        List<Account> accounts = accountDao.getAllAccounts();
        for (Account a : accounts)
            System.out.println(a + "\n");
    }
}
