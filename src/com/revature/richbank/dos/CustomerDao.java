package com.revature.richbank.dos;

import com.revature.richbank.models.Customer;

public class CustomerDao implements Crudable<T> {

    private CustomerDao customerDao = new CustomerDao();

    @Override
    public Customer create(Customer newCustomer) {
        return null;
    }

    @Override
    public Customer[] findAll() throws IOExcetpion {
        return new Customer[0];
    }

    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public boolean update(Customer updateCustomer) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void checkEmail(String email) {
        String sql = "select email_1 from customer where = email_1 = " _+ email + ";" );
    }
}
