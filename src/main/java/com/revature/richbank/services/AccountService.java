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


    private boolean validateAccountInput(Account newAccount){

        System.out.println("AccountService::validateAccountInput() : Validating Account: " + newAccount);

        if(newAccount == null) return false;
        if(newAccount.getAccount_number() == null || newAccount.getAccount_number().trim().equals("")) return false;
        if(newAccount.getAccount_type() == null || newAccount.getAccount_type().trim().equals("")) return false;

        if(newAccount.getFirst_date() == null || newAccount.getFirst_date().trim().equals("")) newAccount.setFirst_date(LocalDate.now().toString());
        if( newAccount.getAccount_type().trim().equals("closed") )
            newAccount.setLast_date(LocalDate.now().toString());
        if(newAccount.getCustomer_id_1() == 0) return false;
        if(newAccount.getTotal() < 0) return false;
        return newAccount.getAccount_number() != null || !newAccount.getAccount_type().trim().equals("");
    }


    @Override
    public Account create(Account newObject) {
        System.out.println("AccountService::create() : Account trying to be registered: " + newObject);

        if(!validateAccountInput(newObject)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("Account information was not validated");
        }

        newObject.setFirst_date(LocalDate.now().toString());

        // TODO: Will implement with JDBC (connecting to our database)
        Account persistedAccount = accountDao.create(newObject);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("AccountService::registerAccount() : Account has been persisted: " + persistedAccount);
        return newObject;
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

    public List<Account> readAll(String customer_id) {
        System.out.println("AccountService::readAll() : reading Accounts with customer ID in file database");

        try {
            List<Account> accountList = new LinkedList<>(); // ignore for now
            logger.info("All accounts for the customer have been found here are the results: \n");
            accountList = accountDao.findAll(customer_id);
            return accountList;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account readById(String account_number) {

        System.out.println("AccountService::readById() : reading an account with account_number: " + account_number);
        try {
            Account account = accountDao.findById(account_number);
            return account;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account update(Account updateObject) {
        System.out.println("AccountService::update() : Customer tries to update : " + updateObject);

        if(!validateAccountInput(updateObject)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if( !accountDao.update(updateObject) ){
            throw new RuntimeException();
        }
        System.out.println("AccountService::update() : Account has been updated: " + updateObject);
        return updateObject;
    }

    @Override
    public boolean delete(String account_number) {
        System.out.println("AccountService::delete() : Customer tries to delete an account: " + account_number);
        logger.info("Customer tries to delete an account: " + account_number);

        if( !customerDao.delete(account_number)){
            throw new RuntimeException();
        }

        return customerDao.delete(account_number);
    }

    public boolean close(String account_number) {
        System.out.println("AccountService::close() : Customer tries to close their account : " + account_number);
        logger.info("Customer tries to close an account: " + account_number);

        return accountDao.close(account_number);
    }

    @Override
    public boolean validInput(Account object) {
        return false;
    }


}
