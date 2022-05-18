package com.revature.richbank.services;


import com.revature.richbank.dos.AccountDao;

import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.models.Account;
import com.revature.richbank.models.Customer;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService implements Serviceable <Account> {

    private AccountDao accountDao;
    private CustomerDao customerDao;
    private Logger logger = Logger.getLogger();

    // DI - Dependency Injection
    public AccountService (AccountDao accountDao, CustomerDao customerDao) {
        this.accountDao = accountDao;
        this.customerDao = customerDao;
    }

    public boolean registerAccount(Account newAccount){
        System.out.println("AccountService::registerAccount() : Account trying to be registered: " + newAccount);

        if(!validateAccountInput(newAccount)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User was not validated");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        Account persistedAccount = accountDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("AccountService::registerAccount() : Account has been persisted: " + persistedAccount);
        return true;
    }

    public boolean updateAccount(Account updateAccount) {
        System.out.println("AccountService::updateAccount() : Account trying to update : " + updateAccount);

        if(!validateAccountInput(updateAccount)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if( !accountDao.update(updateAccount) ){
            throw new RuntimeException();
        }
        System.out.println("AccountService::updateAccount() : Account has been updated: " + updateAccount);
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

    private boolean validateAccountInput(Account newAccount){

        System.out.println("AccountService::validateAccountInput() : Validating Account: " + newAccount);

        if(newAccount == null) return false;
        if(newAccount.getAccount_number() == null || newAccount.getAccount_number().trim().equals("")) return false;
        if(newAccount.getAccount_type() == null || newAccount.getAccount_type().trim().equals("")) return false;
        if(newAccount.getFirst_date() == null || newAccount.getFirst_date().trim().equals("")) newAccount.setFirst_date(LocalDate.now().toString());
        if( newAccount.getAccount_type().trim().equals("closed") && (newAccount.getLast_date() == null || newAccount.getLast_date().trim().equals("")) )
            newAccount.setLast_date(LocalDate.now().toString());
        if(newAccount.getCustomer_id_1() == 0) return false;
        return newAccount.getAccount_number() != null || !newAccount.getAccount_type().trim().equals("");
    }

    @Override
    public Account create(Account newObject) {
        return null;
    }

    @Override
    public List<Account> readAll() {
        System.out.println("AccountService::readAll() : reading Accounts in file database");

        try {
            List<Account> accountList = new LinkedList<>(); // ignore for now
            logger.info("All accounts have been found here are the results: \n");
            accountList = accountDao.findAll();
            return accountList;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Account> readAll(String id) {
        System.out.println("AccountService::readAll() : reading Accounts with customer ID in file database");

        try {
            List<Account> accountList = new LinkedList<>(); // ignore for now
            logger.info("All accounts for the customer have been found here are the results: \n");
            accountList = accountDao.findAll(id);
            return accountList;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account readById(String id) {
        return null;
    }

    @Override
    public Account update(Account updateObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validInput(Account object) {
        return false;
    }
}
