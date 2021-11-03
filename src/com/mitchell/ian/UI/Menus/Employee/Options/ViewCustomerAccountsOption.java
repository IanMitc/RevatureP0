package com.mitchell.ian.UI.Menus.Employee.Options;

import com.mitchell.ian.Account.Account;
import com.mitchell.ian.Data.AccountDao;
import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.UserDao;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

import java.util.List;

public class ViewCustomerAccountsOption {
    public static void execute() {
        Clear.console();
        AccountDao accountDao = DaoFactory.getAccountDao();
        UserDao userDao = DaoFactory.getUserDao();

        int customerId = 0;
        boolean run = true;
        while (run) {
            try {
                customerId = Ask.forInt("Enter the customer id to view accounts (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (customerId == 0) {
            Clear.console();
            System.out.println("Canceled");
            return;
        }
        User user = userDao.getUser(customerId);

        List<Account> accounts = accountDao.getAllAccounts(user);
        for (Account a : accounts)
            System.out.println(a + "\n");
    }
}
