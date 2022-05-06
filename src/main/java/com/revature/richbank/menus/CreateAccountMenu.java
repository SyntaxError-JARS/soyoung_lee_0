package com.revature.richbank.menus;

import com.revature.richbank.models.Account;
import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;

import java.io.BufferedReader;

public class CreateAccountMenu extends Menu {

    private CustomerService customerService = new CustomerService();

    public CreateAccountMenu(BufferedReader terminalReader) {
        super("Update Menu", "/update", terminalReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println("UpdateMenu::render() : rendering Menu");

        System.out.println("What is your login id?");
        String login_id = terminalReader.readLine();

        System.out.println("What is your password");
        String login_password = terminalReader.readLine();


        Customer customer = new Customer();
        customer = customerService.readACustomer(login_id, login_password);

        if ( customer != null ) {
            System.out.println("Great! You successfully login with your ID and Password!");

            String welcome = "If you want to make an account in Rich Bank,";
            String option1 = "please select your account type first";
            String option2 = "1) Deposit";
            String option3 = "2) Checking";

            System.out.printf("%s \n %s \n %s \n %s \n", welcome, option1, option2, option3).println();


            String account_input = terminalReader.readLine();

            Account newAccount = new Account();
            if(account_input.equals("1")) newAccount.setAccount_type("deposit");
            else newAccount.setAccount_type("checking");
            newAccount


                AccountService.create()



            System.out.println("Enter the amount of money you want deposit today: ");
            String passwordCheck = terminalReader.readLine();

            if (!login_password.equals(passwordCheck)) { // password != passwordCheck
                System.out.println("Passwords don't match");
                return; // why return??? Control Flow, this breaks this method and returns us to main
            }

            customer.setLogin_password(login_password);


            if( customerService.updateCustomer(customer) )
                System.out.println("Happy!!!! Your information is successfully updated!");
            else
                System.out.println("Sorry!!!! Your information is not updated!");

        } else  {
            System.out.println("You failed to login with your ID and password! ");
            System.out.println("Please! Check your ID and password!\n\n");
            System.out.println("If you didn't register yet...please register first!!!")
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
