package com.mitchell.ian.Data;

public class DaoFactory {
    private static AccountDao accountDao = null;
    private static UserDao userDao = null;
    private static User2AccountDao user2AccountDao = null;
    private static TransactionDao transactionDao = null;

    private DaoFactory() {
    }

    public static AccountDao getAccountDao() {
        if (accountDao == null)
            accountDao = new AccountDaoImpl();
        return accountDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null)
            userDao = new UserDaoImpl();
        return userDao;
    }

    public static User2AccountDao getUser2AccountDao() {
        if (user2AccountDao == null)
            user2AccountDao = new User2AccountDaoImpl();
        return user2AccountDao;
    }

    public static TransactionDao getTransactionDao() {
        if (transactionDao == null)
            transactionDao = new TransactionDaoImpl();
        return transactionDao;
    }
}
