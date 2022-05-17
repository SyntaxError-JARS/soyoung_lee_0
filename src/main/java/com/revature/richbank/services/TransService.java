package com.revature.richbank.services;


import com.revature.richbank.dos.AccountDao;
import com.revature.richbank.dos.TransDao;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.models.Trans;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TransService implements Serviceable<Trans> {

    private TransDao transDao = new TransDao();
    private final Logger logger = Logger.getLogger(true);

    public TransService (TransDao transDao) {   this.transDao = transDao; }

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
        System.out.println("TransService::create() : Trans trying to be registered: " + newObject);

        if(!validateTransInput(newObject)){ // checking if false
            //System.out.println("User was not validated");
            throw new InvalidRequestException("User was not validated");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        Trans persistedTrans = transDao.create(newObject);

        if(persistedTrans == null){
            throw new RuntimeException();
        }
        System.out.println("TransService::registerTrans() : Trans has been persisted: " + persistedTrans);
        return newObject;
    }

    @Override
    public List<Trans> readAll() {
        return null;
    }

    public List<Trans> readAll(String id) {
        System.out.println("TransService::readAll() : reading Transaction List with using id in file database");

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
        return null;
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
