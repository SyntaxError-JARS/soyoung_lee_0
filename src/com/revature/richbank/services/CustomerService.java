package com.revature.richbank.services;


import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.models.Customer;

import java.io.IOException;

public class CustomerService {

    private CustomerDao customerDao = new CustomerDao();

    public void readCustomers (){
        System.out.println("CustomerService::readCustomers() : reading Customers in file database");

        Customer[] customers = new Customer[0]; // ignore for now
        try {
            customers = customerDao.findAll();

            for (int i = 0; i < customers.length; i++) {
                Customer customer = customers[i];
                System.out.println(customer.toString());
            }
        } catch ( NullPointerException e){
            //e.printStackTrace();
        }
    }


    public boolean validateLogin_IDNotUsed(String login_id){
        System.out.println("CustomerService::validateLogin_IDNotUsed() : checking Login ID validation.");

        customerDao.checkLogin_ID(login_id);
        return false;
    }

    public boolean registerCustomer(Customer newCustomer){
        System.out.println("CustomerService::registerCustomer() : Customer trying to be registered: " + newCustomer);        System.out.println("Trainer trying to be registered: " + newCustomer);

        if(!validateCustomerInput(newCustomer)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateLogin_IDNotUsed(newCustomer.getLogin_id());

        Customer persistedCustomer = customerDao.create(newCustomer);

        if(persistedCustomer == null){
            throw new RuntimeException();
        }
        System.out.println("CustomerService::registerCustomer() : Customer has been persisted: " + persistedCustomer);
        return true;
    }

    private boolean validateCustomerInput(Customer newCustomer){

        System.out.println("CustomerService::validateCustomerInput() : Validating Customer: " + newCustomer);

        if(newCustomer == null) return false;
        if(newCustomer.getCustomer_name() == null || newCustomer.getCustomer_name().trim().equals("")) return false;
        if(newCustomer.getEmail_1() == null || newCustomer.getEmail_1().trim().equals("")) return false;
        if(newCustomer.getPhone_1() == null || newCustomer.getPhone_1().trim().equals("")) return false;
        if(newCustomer.getAddress() == null || newCustomer.getAddress().trim().equals("")) return false;
        if(newCustomer.getLogin_id() == null || newCustomer.getLogin_id().trim().equals("")) return false;
        return newCustomer.getLogin_password() != null || !newCustomer.getLogin_password().trim().equals("");
    }

}
