package com.revature.richbank.menus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterMenu extends Menu {
    public RegisterMenu (BufferedReader terminalReader) {
        super("Register Menu", "/register", terminalReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println("What is your full name?");
        String fullName = terminalReader.readLine();


        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();
        ;

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("DOB?");
        String dob = terminalReader.readLine();

        this.account_number = account_number;
        this.customer_name = customer_name;
        this.email_1 = email_1;
        this.customer_id = customer_id;
        this.phone_1 = phone_1;
        this.address = address;


        private int    fail_count    ;
        private String account_number;
        private String customer_name ;
        private String email_1       ;
        private int    customer_id   ;
        private String phone_1       ;
        private String address       ;

        // What's happening here???
        // Breaking or splitting the String fullName into an String array by " " spaces
        String[] nameArray = fullName.split(" ");
        String fname = nameArray[0];
        String lname = nameArray[nameArray.length - 1];

        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new Trainer object in memory
        Trainer newTrainer = new Trainer(fname, lname, email, password, dob);
        System.out.println(newTrainer); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        // What's this??? Obtaining the file from the relative path
        File trainerPersist = new File("resources/data.txt"); // Note check out maxwells stuff.

        // What's happening here???
        // try-with-resoruces - it works with auto-closable classes.
        try(FileWriter fileWriter = new FileWriter(trainerPersist, true)) {
            fileWriter.write(newTrainer.toFileString() + "\n"); // write is method to write into the specified fill
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

