package com.revature.richbank.services;


import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.exceptions.AuthenticationException;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.exceptions.ResourcePersistenceException;
import com.revature.richbank.models.Account;
import com.revature.richbank.models.Customer;
import com.revature.richbank.util.logging.Logger;


import java.awt.color.ICC_ColorSpace;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService  implements Serviceable <Customer> {

    private CustomerDao customerDao;
    private Logger logger = Logger.getLogger();

    public CustomerService (CustomerDao customerDao) {   this.customerDao = customerDao; }


    public boolean validateLogin_IDNotUsed(String login_id){
        System.out.println("CustomerService::validateLogin_IDNotUsed() : checking Login ID validation.");

        customerDao.checkLogin_ID(login_id);
        return false;
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
        if(newCustomer.getLogin_password() == null || newCustomer.getLogin_password().trim().equals("")) return false;
        return (newCustomer.getLogin_id() != null || !newCustomer.getLogin_id().trim().equals(""));
    }

    public Customer authenticateCustomer(String login_id, String login_password) {

        if (login_id == null || login_id.equals("") || login_password == null || login_password.equals("")) {
            throw new InvalidRequestException("Either id or password is an invalidate entry. Please logging in again!");
        }

        Customer authenticateCustomer = customerDao.authenticateCustomer(login_id, login_password);

        if (authenticateCustomer == null) {
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticateCustomer;
    }

    @Override
    public Customer create(Customer newObject) {
        System.out.println("CustomerService::create() : Customer trying to be registered: " + newObject);

        if(!validateCustomerInput(newObject)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User input was not validated, empty string or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateLogin_IDNotUsed(newObject.getLogin_id());

        Customer persistedCustomer = customerDao.create(newObject);

        if(persistedCustomer == null){
            throw new ResourcePersistenceException("Customer was not persisted to the database upon registration.");
        }
        System.out.println("CustomerService::registerCustomer() : Customer has been persisted: " + persistedCustomer);
        return newObject;
    }

    @Override
    public List<Customer> readAll() {
        System.out.println("CustomerService::readAll() : reading Customers in file database");

        try {
            List<Customer> customerList = customerDao.findAll();
            logger.info("All customers have been found here are the results: \n");

            return customerList;

        } catch ( IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer readById(String id) {

        System.out.println("CustomerService::readById() : reading a Customers with login_id");
        try {
            Customer customer = customerDao.findById(id);
            return customer;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }


    public Customer readById(String id, String password) {
        System.out.println("CustomerService::readById() : reading a Customers with id and password in file database");

        Customer customer = null;

        try {
            customer = customerDao.findById(id, password);
            //customer = customerDao.findById(login_id);

            if ( customer != null ) System.out.println("CustomerService::readACustomer() : " + customer);

        } catch ( NullPointerException e){
             e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer update(Customer updateObject) {
        System.out.println("CustomerService::updateCustomer() : Customer trying to update : " + updateObject);
        logger.info("Customer try to be update: " + updateObject);

        if(!validateCustomerInput(updateObject)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        String login_id = updateObject.getLogin_id();
        validateLogin_IDNotUsed(login_id);

        if( !customerDao.update(updateObject) ){
            throw new RuntimeException();
        }

        updateObject.setCustomer_id(customerDao.checkCustomer_ID(login_id));

        System.out.println("CustomerService::updateCustomer() : Customer has been updated: " + updateObject);
        return updateObject;
    }

    @Override
    public boolean delete(String id) {
        System.out.println("CustomerService::delete() : Customer trying to delete : " + id);
        logger.info("Customer try to be delete: " + id);

        return customerDao.delete(id);

    }

    @Override
    public boolean validInput(Customer object) {
        return false;
    }
}
