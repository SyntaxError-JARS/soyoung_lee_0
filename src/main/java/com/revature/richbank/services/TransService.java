package com.revature.richbank.services;


import com.revature.richbank.dos.AccountDao;
import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.dos.TransDao;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.exceptions.ResourcePersistenceException;
import com.revature.richbank.models.Customer;
import com.revature.richbank.models.Trans;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TransService implements Serviceable<Trans> {

    private final TransDao transDao;
    private final AccountDao accountDao;
    private  final CustomerDao customerDao;
    private Logger logger = Logger.getLogger();

    // DI - Dependency Injection
    public TransService (TransDao transDao, AccountDao accountDao, CustomerDao customerDao) {
        this.transDao = transDao;
        this.accountDao = accountDao;
        this.customerDao = customerDao;
    }

    private boolean validateTransInput(Trans newTrans){

        System.out.println("TransService::validateTransInput() : Validating Trans: " + newTrans);

        if(newTrans == null) return false;
        if(newTrans.getAccount_number() == null || newTrans.getAccount_number().equals("")) return false;
        if(newTrans.getTrans_type() == null || newTrans.getTrans_type().trim().equals("")) return false;
        if(newTrans.getTrans_date() == null || newTrans.getTrans_date().trim().equals("")) return false;
        if(newTrans.getAmount() < 0.00 ) return false;
        return newTrans.getAccount_number() != null || !newTrans.getTrans_type().trim().equals("") || newTrans.getAmount() < 0.00;
    }

    @Override
    public Trans create(Trans newObject) {

        logger.info("Customer tries to make a transaction: " + newObject);
        if(!validateTransInput(newObject)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User was not validated");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        Trans persistedTrans = transDao.create(newObject);

        if(persistedTrans == null){
            new ResourcePersistenceException("Transaction was not persisted to the database");
        }
        logger.info("The transaction has been persisted: " + newObject);
        return newObject;
    }

    @Override
    public List<Trans> readAll() {
        return null;
    }

    public List<Trans> readAll(String id) {
        System.out.println("TransService::readAll() : reading Transaction List with using account_number in file database");

        List<Trans> transList = new LinkedList<>(); // ignore for now
        try {
            transList = transDao.findAll(id);


            // For Each loop
            // for (Object trans : transs )
            //      if ( trans != null ) System.out.println((Trans)trans);
            for (Trans trans : transList)   // trans indicates a single element in the array transs
                if ( trans != null ) System.out.println(trans);

        } catch (NullPointerException | IOException e){
            //e.printStackTrace();
        }
        return transList;
    }

    @Override
    public Trans readById(String id) {

        System.out.println("TransService::readById() : reading a Transaction with trans_id in file database");

        Trans trans = null;

        try {
            trans = transDao.findById(id);
            //customer = customerDao.findById(login_id);

            if (trans != null) System.out.println("TransService::readById() : " + id);

        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
        return trans;
    }



    @Override
    public Trans update(Trans updateObject) {
        System.out.println("TransService::updateTrans() : Trans trying to update : " + updateObject);

        if(!validateTransInput(updateObject)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if( !transDao.update(updateObject) ){
            throw new RuntimeException();
        }
        System.out.println("TransService::updateTrans() : Trans has been updated: " + updateObject);
        return updateObject;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validInput(Trans object) {
        return false;
    }
}
