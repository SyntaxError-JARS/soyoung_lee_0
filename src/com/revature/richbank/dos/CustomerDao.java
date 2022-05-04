package com.revature.richbank.dos;

public class CustomerDao implements Crudable<T> {

    private CustomerDao customerDao = new CustomerDao();

    public void
    @Override
    public T create(T newObject) {
        return null;
    }

    @Override
    public T[] findAlll() {
        return new T[0];
    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public boolean update(T updateObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
