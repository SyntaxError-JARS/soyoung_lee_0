package com.revature.richbank.menus;

import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterMenu extends Menu {

    private CustomerService customerService = new CutomerService();

    public RegisterMenu (BufferedReader terminalReader) {
        super("Register Menu", "/register", terminalReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println("What is your name?");
        String customer_name = terminalReader.readLine();

        System.out.println("What is your email?");
        String email_1 = terminalReader.readLine();

        System.out.println("What is your phone number?");
        String phone_1 = terminalReader.readLine();

        System.out.println("What is your address?");
        String address = terminalReader.readLine();

        System.out.println("What is your login id?");
        String login_id = terminalReader.readLine();

        System.out.println("What is your password");
        String login_password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("DOB?");
        String dob = terminalReader.readLine();


        // What's happening here??
        //
        if (!login_password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new Trainer object in memory
        Customer newCustomer = new Customer(customer_name, email_1, phone_1, address, login_id, login_password, "05/04/2022", false)
        System.out.println("Here is the trainer that was provided by the user: " + newCustomer);
        customerService.registerCustomer(newCustomer);
    }
}

