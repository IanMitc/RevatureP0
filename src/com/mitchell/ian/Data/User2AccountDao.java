package com.mitchell.ian.Data;

import java.util.List;

public interface User2AccountDao {
    List<Integer> getAccounts(int userId);

    List<Integer> getUsers(int accountId);

    void connect(int userId, int accountId);

    void disconnect(int userId, int accountId);
}
