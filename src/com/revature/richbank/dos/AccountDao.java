package com.revature.richbank.dos;

import com.revature.richbank.models.Account;

import java.io.IOException;

public class AccountDao implements Crudable<Account> {
    @Override
    public Account create(Account newAccount) {
        return null;
    }

    @Override
    public Account[] findAll() throws IOException {
        return new Account[0];
    }

    @Override
    public Account findById(String id) {
        return null;
    }

    @Override
    public boolean update(Account updateAccount) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
