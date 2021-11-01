package com.mitchell.ian.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static void initialize() {
        initializeTables();
        initializeStoredProcedures();
    }

    private static void initializeTables() {
        Connection connection = ConnectionFactory.getConnection();

        String getTablesSql = "show tables";
        String createUserTableSql = "CREATE TABLE `users` (" +
                "`id` int NOT NULL AUTO_INCREMENT," +
                "`name` char(50) NOT NULL," +
                "`email` char(50) NOT NULL," +
                "`role` enum('SYSTEM','ADMIN','EMPLOYEE','CUSTOMER') DEFAULT NULL," +
                "`accounts` int DEFAULT NULL," +
                "`password` char(64) NOT NULL," +
                "PRIMARY KEY (`id`)," +
                "UNIQUE KEY `email` (`email`)," +
                "KEY `ix_users_id` (`id`)," +
                "KEY `ix_users_email` (`email`)" +
                ")";
        String createTransactionsTableSql = "CREATE TABLE `transactions` (" +
                "`id` int(11) NOT NULL AUTO_INCREMENT," +
                "`amount` decimal(15,2) NOT NULL," +
                "`from_account` int NOT NULL," +
                "`to_account` int NOT NULL," +
                "`initiated_by` int NOT NULL," +
                "`date_initiated` datetime NOT NULL," +
                "`completed_by` int DEFAULT NULL," +
                "`date_completed` datetime DEFAULT NULL," +
                "`require_user_approval` boolean DEFAULT NULL," +
                "`pending` boolean DEFAULT NULL," +
                "PRIMARY KEY (`id`)," +
                "KEY `ix_transactions_id` (`id`)," +
                "KEY `ix_transactions_from_account` (`from_account`)," +
                "KEY `ix_transactions_to_account` (`to_account`)," +
                "KEY `ix_transactions_completed_by` (`completed_by`)," +
                "KEY `ix_transactions_initiated_by` (`initiated_by`)," +
                "KEY `ix_transactions_pending` (`pending`)," +
                "KEY `ix_transactions_date_completed` (`date_completed`)," +
                "KEY `ix_transactions_date_initiated` (`date_initiated`)" +
                ")";
        String createAccountsTable = "CREATE TABLE `accounts` (" +
                "`id` int NOT NULL AUTO_INCREMENT," +
                "`balance` decimal(15,2) NOT NULL," +
                "`owners` int NOT NULL," +
                "`transactions` int DEFAULT NULL," +
                "`credit_locked` boolean DEFAULT NULL," +
                "`debit_locked` boolean DEFAULT NULL," +
                "`pending` boolean DEFAULT NULL," +
                "PRIMARY KEY (`id`)," +
                "KEY `ix_account_id` (`id`)," +
                "KEY `ix_account_pending` (`pending`)" +
                ")";

        try {
            List<String> tables = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getTablesSql);

            while (rs.next()) {
                tables.add(rs.getString(1));
            }

            if (!tables.contains("users")){
                statement.executeUpdate(createUserTableSql);
            }

            if (!tables.contains("transactions")){
                statement.executeUpdate(createTransactionsTableSql);
            }

            if (!tables.contains("accounts")){
                statement.executeUpdate(createAccountsTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeStoredProcedures() {
        Connection connection = ConnectionFactory.getConnection();

        String getStoredProceduresSql ="show procedure status where db = 'the_bank'";
        String dropProcedure = "DROP PROCEDURE IF EXISTS ";
        String createGetUserById = "create procedure sp_get_user_by_id (IN var1 INTEGER) " +
                "BEGIN " +
                "select * from user where id = var1; " +
                "END";
        try {
            Statement statement = connection.createStatement();
            //statement.executeUpdate(createGetUserById);
            statement.addBatch(dropProcedure + "sp_get_user_by_id");
            statement.addBatch(createGetUserById);

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}