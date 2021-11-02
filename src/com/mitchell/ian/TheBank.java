package com.mitchell.ian;

import com.mitchell.ian.Data.*;
import com.mitchell.ian.UI.Menus.Shared.MainMenu;

public class TheBank {

    public static void main(String[] args) {
        Database.initialize();

        MainMenu.show();


//
//        Connection connection = ConnectionFactory.getConnection();
//        TransactionDao transactionDao = DaoFactory.getTransactionDao();
//        UserDao userDao = DaoFactory.getUserDao();
//        AccountDao accountDao = DaoFactory.getAccountDao();
//
//        Account account = accountDao.getAccount(1);
//        User user = userDao.getUser(1);
//
//        Transaction transaction = null;
//
//
//        try {
//            transaction = new Transaction(500.00, account, account, "", user, false);
//        } catch (InvalidAmountException e) {
//            e.printStackTrace();
//        }
//
//        transactionDao.addTransaction(transaction);
    }
}
