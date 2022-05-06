package com.revature.richbank.services;


import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.models.Customer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {

    private CustomerDao customerDao = new CustomerDao();

    public void readCustomers (){
        System.out.println("CustomerService::readCustomers() : reading Customers in file database");

        Customer[] customers = new Customer[0]; // ignore for now
        try {
            customers = customerDao.findAll();

            for (int i = 0; i < customers.length; i++) {
                Customer customer = customers[i];
                if ( customer != null )
                        System.out.println(customer);
            }

            // For Each loop
            // for (Object customer : customers )
            //      if ( customer != null ) System.out.println((Customer)customer);
            for (Customer customer : customers)   // customer indicates a single element in the array customers
                if ( customer != null ) System.out.println(customer);

        } catch ( NullPointerException e){
            //e.printStackTrace();
        }
    }

    public Customer readACustomer(String login_id, String login_password){
        System.out.println("CustomerService::readACustomer() : reading a Customers with id and password in file database");

        Customer customer = null;

        try {
            customer = customerDao.findById(login_id, login_password);
            //customer = customerDao.findById(login_id);

            if ( customer != null ) System.out.println("CustomerService::readACustomer() : " + customer);

        } catch ( NullPointerException e){
            //e.printStackTrace();
        }
        return customer;
    }


    public boolean validateLogin_IDNotUsed(String login_id){
        System.out.println("CustomerService::validateLogin_IDNotUsed() : checking Login ID validation.");

        customerDao.checkLogin_ID(login_id);
        return false;
    }

    public boolean registerCustomer(Customer newCustomer){
        System.out.println("CustomerService::registerCustomer() : Customer trying to be registered: " + newCustomer);

        if(!validateCustomerInput(newCustomer)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User was not validated");
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

    public boolean updateCustomer(Customer updateCustomer) {
        System.out.println("CustomerService::updateCustomer() : Customer trying to update : " + updateCustomer);

        if(!validateCustomerInput(updateCustomer)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateLogin_IDNotUsed(updateCustomer.getLogin_id());

        if( !customerDao.update(updateCustomer) ){
            throw new RuntimeException();
        }
        System.out.println("CustomerService::updateCustomer() : Customer has been updated: " + updateCustomer);
        return true;
    }

    public boolean isValidEmailAddress(String email) {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        // InternetAddreess internetAddreess = new InternetAddressemail(email);
        // internetAddreess.validate();
        return email.matches(regex);
    }

    public boolean isValidPassword(String password) {
        if ( password.length() >= 8 ) {
            Pattern letter = Pattern.compile("[a-zA-Z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            // Pattern length = Pattern.compile(".{8}"); // length check

            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasDigit.find() && hasSpecial.find();
        } else
            return false;

        // String regex = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        // return password.matches(regex);
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
