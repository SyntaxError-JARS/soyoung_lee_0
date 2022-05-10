package com.revature.richbank.services;


import com.revature.richbank.dos.TransDao;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.models.Trans;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransService {

    private TransDao transDao = new TransDao();

    public void readTrans (String account_number){
        System.out.println("TransService::readTranss() : reading Transs in file database");

        Trans[] transs = new Trans[0]; // ignore for now
        try {
            transs = transDao.findAll(account_number);


            // For Each loop
            // for (Object trans : transs )
            //      if ( trans != null ) System.out.println((Trans)trans);
            for (Trans trans : transs)   // trans indicates a single element in the array transs
                if ( trans != null ) System.out.println(trans);

        } catch (NullPointerException | IOException e){
            //e.printStackTrace();
        }
    }

    public Trans readATrans(String trans_id){
        logger.info("TransService::readATrans() : reading a Trans with trans_id");

        Trans trans = null;

        try {
            trans = transDao.findById(trans_id);

            if ( trans != null ) logger.info("TransService::readATrans() : " + trans);

        } catch ( NullPointerException e){
            //e.printStackTrace();
        }
        return trans;
    }


    public boolean validateLogin_IDNotUsed(String login_id){
        System.out.println("TransService::validateLogin_IDNotUsed() : checking Login ID validation.");

        transDao.checkLogin_ID(login_id);
        return false;
    }

    public boolean registerTrans(Trans newTrans){
        System.out.println("TransService::registerTrans() : Trans trying to be registered: " + newTrans);

        if(!validateTransInput(newTrans)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User was not validated");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateLogin_IDNotUsed(newTrans.getLogin_id());

        Trans persistedTrans = transDao.create(newTrans);

        if(persistedTrans == null){
            throw new RuntimeException();
        }
        System.out.println("TransService::registerTrans() : Trans has been persisted: " + persistedTrans);
        return true;
    }

    public boolean updateTrans(Trans updateTrans) {
        System.out.println("TransService::updateTrans() : Trans trying to update : " + updateTrans);

        if(!validateTransInput(updateTrans)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateLogin_IDNotUsed(updateTrans.getLogin_id());

        if( !transDao.update(updateTrans) ){
            throw new RuntimeException();
        }
        System.out.println("TransService::updateTrans() : Trans has been updated: " + updateTrans);
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

    private boolean validateTransInput(Trans newTrans){

        System.out.println("TransService::validateTransInput() : Validating Trans: " + newTrans);

        if(newTrans == null) return false;
        if(newTrans.getTrans_name() == null || newTrans.getTrans_name().trim().equals("")) return false;
        if(newTrans.getEmail_1() == null || newTrans.getEmail_1().trim().equals("")) return false;
        if(newTrans.getPhone_1() == null || newTrans.getPhone_1().trim().equals("")) return false;
        if(newTrans.getAddress() == null || newTrans.getAddress().trim().equals("")) return false;
        if(newTrans.getLogin_id() == null || newTrans.getLogin_id().trim().equals("")) return false;
        return newTrans.getLogin_password() != null || !newTrans.getLogin_password().trim().equals("");
    }

}
