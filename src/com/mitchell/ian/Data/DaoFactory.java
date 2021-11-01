package com.mitchell.ian.Data;

public class DaoFactory {
    private static UserDao userDao = null;
    private static AccountDao accountDao = null;
    private static TransactionDao transactionDao = null;

    private DaoFactory() {
    }

    public static UserDao getUserDao() {
        if (userDao == null)
            userDao = new UserDaoImpl();
        return userDao;
    }

    public static AccountDao getAccountDao() {
        if (accountDao == null)
            accountDao = new AccountDaoImpl();
        return accountDao;
    }

    public static TransactionDao getTransactionDao() {
        if (transactionDao == null)
            transactionDao = new TransactionDaoImpl();
        return transactionDao;
    }
}
