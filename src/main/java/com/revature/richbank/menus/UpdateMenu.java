package com.revature.richbank.menus;

import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;
import com.revature.richbank.util.logging.Logger;

import java.io.BufferedReader;

public class UpdateMenu extends Menu {

    private CustomerService customerService = new CustomerService();

    private final Logger logger = Logger.getLogger(true);

    public UpdateMenu (BufferedReader terminalReader) {
        super("Update Menu", "/update", terminalReader);
    }

    @Override
    public void render() throws Exception {
        logger.info("UpdateMenu::render() : rendering Menu");

        System.out.println("What is your login id?");
        String login_id = terminalReader.readLine();

        System.out.println("What is your password");
        String login_password = terminalReader.readLine();


        Customer customer = new Customer();
        customer = customerService.readACustomer(login_id, login_password);

        if ( customer != null ) {
            System.out.println("Great! You successfully login with your ID and Password!");

            showCustomerInfo(customer);

            System.out.println("Please enter your new password?");
            login_password = terminalReader.readLine();

            System.out.println("Re-enter your new password");
            String passwordCheck = terminalReader.readLine();

            if (!login_password.equals(passwordCheck)) { // password != passwordCheck
                System.out.println("Passwords don't match");
                return; // why return??? Control Flow, this breaks this method and returns us to main
            }

            customer.setLogin_password(login_password);


            if( customerService.updateCustomer(customer) )
                logger.info("Happy!!!! Your information is successfully updated!");
            else
                logger.warn("Sorry!!!! Your information is not updated!");

        } else  {
            logger.warn("You failed to login with your ID and password! ");
            logger.warn("Please! Check your ID and paassword!");
        }


    }


    public void showCustomerInfo(Customer customer) {
        System.out.println("Login ID: " + customer.getLogin_id());
        System.out.println("Your name: " + customer.getCustomer_name());
        System.out.println("Your email: " + customer.getEmail_1());
        System.out.println("Your phone: " + customer.getPhone_1());
        System.out.println("Your address: " + customer.getAddress());
        System.out.println("Your password: " + customer.getLogin_password());
    }

    public void getUpdateInfo() {

    }
}
