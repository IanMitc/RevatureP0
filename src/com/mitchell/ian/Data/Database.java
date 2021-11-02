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
        populateDefaultData();
    }

    private static void populateDefaultData() {
    }

    private static void initializeTables() {
        Connection connection = ConnectionFactory.getConnection();

        String getTablesSql = "show tables";
        String createUserTableSql = "CREATE TABLE `users` (" +
                "`id` int NOT NULL AUTO_INCREMENT," +
                "`name` char(50) NOT NULL," +
                "`email` char(50) NOT NULL," +
                "`role` enum('SYSTEM','ADMIN','EMPLOYEE','CUSTOMER') DEFAULT NULL," +
                "`password` char(64) NOT NULL," +
                "PRIMARY KEY (`id`)," +
                "UNIQUE KEY `email` (`email`)," +
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
                "`memo` varchar(100) DEFAULT NULL," +
                "PRIMARY KEY (`id`)," +
                "KEY `ix_transactions_from_account` (`from_account`)," +
                "KEY `ix_transactions_to_account` (`to_account`)," +
                "KEY `ix_transactions_completed_by` (`completed_by`)," +
                "KEY `ix_transactions_initiated_by` (`initiated_by`)," +
                "KEY `ix_transactions_pending` (`pending`)," +
                "KEY `ix_transactions_date_completed` (`date_completed`)," +
                "KEY `ix_transactions_date_initiated` (`date_initiated`)," +
                "CONSTRAINT `fk_completed_by` FOREIGN KEY (`completed_by`) REFERENCES `users` (`id`)," +
                "CONSTRAINT `fk_initiated_by` FOREIGN KEY (`initiated_by`) REFERENCES `users` (`id`)," +
                "CONSTRAINT `fk_from_account` FOREIGN KEY (`from_account`) REFERENCES `accounts` (`id`)," +
                "CONSTRAINT `fk_to_account` FOREIGN KEY (`to_account`) REFERENCES `accounts` (`id`)" +
                ")";
        String createAccountsTable = "CREATE TABLE `accounts` (" +
                "`id` int NOT NULL AUTO_INCREMENT," +
                "`balance` decimal(15,2) NOT NULL," +
                "`credit_locked` boolean DEFAULT NULL," +
                "`debit_locked` boolean DEFAULT NULL," +
                "`pending` boolean DEFAULT NULL," +
                "`closed` boolean DEFAULT false," +
                "PRIMARY KEY (`id`)," +
                "KEY `ix_account_pending` (`pending`)" +
                ")";
        String createUser2AccountTable = "CREATE TABLE `user2account` (" +
                "`user_id` int NOT NULL," +
                "`account_id` int NOT NULL," +
                "PRIMARY KEY (`user_id`,`account_id`)," +
                "KEY `ix_user2account_account_id` (`account_id`)," +
                "KEY `ix_user2account_user_id` (`user_id`), " +
                "UNIQUE KEY `ix_user2account_reverse_pk` (`account_id`,`user_id`)," +
                "CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE," +
                "CONSTRAINT `fk_account_id` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")";

        try (Statement statement = connection.createStatement()) {
            List<String> tables = new ArrayList<>();

            ResultSet rs = statement.executeQuery(getTablesSql);
            while (rs.next()) {
                tables.add(rs.getString(1));
            }

            if (!tables.contains("users")) {
                statement.addBatch(createUserTableSql);
            }

            if (!tables.contains("accounts")) {
                statement.addBatch(createAccountsTable);
            }

            if (!tables.contains("user2account")) {
                statement.addBatch(createUser2AccountTable);
            }

            if (!tables.contains("transactions")) {
                statement.addBatch(createTransactionsTableSql);
            }

            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeStoredProcedures() {
        Connection connection = ConnectionFactory.getConnection();

        String getStoredProceduresSql = "show procedure status where db = 'the_bank'";
        String dropProcedure = "DROP PROCEDURE IF EXISTS ";
        String createGetUserById = "create procedure getUserById (IN varId INTEGER) " +
                "BEGIN " +
                "select * from user where id = varId; " +
                "END";
        try (Statement statement = connection.createStatement()) {
            statement.addBatch(dropProcedure + "getUserById");
            statement.addBatch(createGetUserById);

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}