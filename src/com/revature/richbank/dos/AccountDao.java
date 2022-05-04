package com.revature.richbank.dos;

public class AccountDao implements Crudable {
    @Override
    public Object create(Object newObject) {
        return null;
    }

    @Override
    public Object[] findAll() {
        return new Object[0];
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public boolean update(Object updateObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
