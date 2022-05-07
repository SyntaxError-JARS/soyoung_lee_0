package com.revature.richbank.menus;

import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;
import com.revature.richbank.util.logging.Logger;

import java.io.BufferedReader;

public class LoginMenu extends Menu {

    private CustomerService customerService = new CustomerService();

    private final Logger logger = Logger.getLogger(true);

    public LoginMenu (BufferedReader terminalReader) {
        super("Login Menu", "/login", terminalReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println("LoginMenu::render() : rendering Menu");

        System.out.println("What is your login id?");
        String login_id = terminalReader.readLine();

        System.out.println("What is your password");
        String login_password = terminalReader.readLine();


        Customer customer = new Customer();
        customer = customerService.readACustomer(login_id, login_password);
        if ( customer != null ) {
            System.out.println("Hello! You successfully login with your ID and Password!");
        } else  {
            System.out.println("You failed to login with your ID and password! ");
            System.out.println("Please! Check your ID and paassword!");
        }

    }
}
