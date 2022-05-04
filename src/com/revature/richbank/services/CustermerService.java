package com.revature.richbank.services;

import com.revature.richbank.dos.Crudable;

public class CustomerService {


    public static void readCustomer(){
        Account[] accounts = readAccount(); // ignore for now
        // What's happening in the for loop?
        // very common, intialization; conditional;increment (happens AFTER each iteration)
        // 1) intialization occurs ONCE at the beginning
        // 2) conditional is checked afterwards;
        // 3) the statements with in the for loop are run ONE TIME
        // 4) then the increment occurs
        // 5) condidtion is revalated, if true go through the statements again using the reassing i variable
        for (int i = 0; i < accounts.length; i++) {
            Account account = accounts[i];
            System.out.println(account.toString());
        }


    }


    public boolean reasigterTrainer(Trainer newTrainer){
        if(!validateTrainerInput(newTrainer)){
            throw new RuntimeExceptin()
        }
        return true;
    }

    private boolean validateTrainerInput(Trainer newTrainer){
        if(newTrainer == null) return false;
        else {

        }
    }

}
