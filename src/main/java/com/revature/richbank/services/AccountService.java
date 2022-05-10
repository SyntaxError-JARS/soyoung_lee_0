package com.revature.richbank.services;


import com.revature.richbank.dos.AccountDao;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.models.Account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService {

    private AccountDao accountDao = new AccountDao();

    public void readAccounts (){
        System.out.println("AccountService::readAccounts() : reading Accounts in file database");

        Account[] accounts = new Account[0]; // ignore for now
        try {
            accounts = accountDao.findAll();

            for (int i = 0; i < accounts.length; i++) {
                Account account = accounts[i];
                if ( account != null )
                        System.out.println(account);
            }

            // For Each loop
            // for (Object account : accounts )
            //      if ( account != null ) System.out.println((Account)account);
            for (Account account : accounts)   // account indicates a single element in the array accounts
                if ( account != null ) System.out.println(account);

        } catch ( NullPointerException e){
            //e.printStackTrace();
        }
    }

    public boolean validateLogin_IDNotUsed(String login_id){
        System.out.println("AccountService::validateLogin_IDNotUsed() : checking Login ID validation.");

        customerDao.checkLogin_ID(login_id);
        return false;
    }

    public boolean registerAccount(Account newAccount){
        System.out.println("AccountService::registerAccount() : Account trying to be registered: " + newAccount);

        if(!validateAccountInput(newAccount)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User was not validated");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateLogin_IDNotUsed(newAccount.getLogin_id());

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
        validateLogin_IDNotUsed(updateAccount.getLogin_id());

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
        if(newAccount.getAccount_name() == null || newAccount.getAccount_name().trim().equals("")) return false;
        if(newAccount.getEmail_1() == null || newAccount.getEmail_1().trim().equals("")) return false;
        if(newAccount.getPhone_1() == null || newAccount.getPhone_1().trim().equals("")) return false;
        if(newAccount.getAddress() == null || newAccount.getAddress().trim().equals("")) return false;
        if(newAccount.getLogin_id() == null || newAccount.getLogin_id().trim().equals("")) return false;
        return newAccount.getLogin_password() != null || !newAccount.getLogin_password().trim().equals("");
    }

}
