package com.revature.richbank.menus;

import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.exceptions.ResourcePersistenceException;
import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;
import com.revature.richbank.util.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// Inheritance from menu abstract class: another pillar of OOP
public class RegisterMenu extends Menu {

    private CustomerService customerService = new CustomerService();

    private final Logger logger = Logger.getLogger(true);

    public RegisterMenu (BufferedReader terminalReader) {
        super("Register Menu", "/register", terminalReader);
    }

    @Override
    public void render() throws Exception {
        logger.info("RegisterMenu::render() : rendering Menu");

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

        // System.out.println("DOB?");
        // String dob = terminalReader.readLine();


        // What's happening here??
        //
        if (!login_password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new Trainer object in memory
        Customer newCustomer = new Customer(customer_name, email_1, phone_1, address, login_id, login_password, false);
        logger.info("Here is the customer that was provided by the user: " + newCustomer);
        try {
            customerService.registerCustomer(newCustomer);
        } catch (InvalidRequestException | ResourcePersistenceException e) {
            logger.warn(e.getMessage());
            e.getStackTrace();
        }
    }
}

