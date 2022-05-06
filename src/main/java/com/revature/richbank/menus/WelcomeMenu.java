package com.revature.richbank.menus;

import com.revature.richbank.services.CustomerService;
import com.revature.richbank.util.logging.Logger;

import java.io.BufferedReader;

import static com.revature.richbank.util.AppState.shutdown;

public class WelcomeMenu extends Menu {

    private CustomerService customerService;

    private final Logger logger ;


    public WelcomeMenu(BufferedReader terminalReader, CustomerService customerService, Logger logger) {
        super("Welcome", "/welcome", terminalReader);
        this.customerService = customerService;
        this.logger = logger;
    }



    @Override
    public void render() throws Exception {
        System.out.println("WelcomeMenu::render() : rendering Menu");

        String welcome = "Welcome to the Rich Bank!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) Update Information";
        String option4 = "4) Create your account";
        String option5 = "5) View all transactions for your account";
        String option6 = new String("6) Exit the Rich Bank"); // This is the same as ""


        // This is where we take the above variables and print them using a formatter
        // %s for STrings or %d for digits (numbers) in the string provided initially, then you provide the respective variables
        // println() is just invoking another method from out to add a new line after
        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5, option6).println();

        System.out.print("\n Select number from above\n >"); // just print, prints out to console and that's it, no appended new line
        // What's happening here?
        // Taking input from the readline in terminalReader as a String!!! Always a STRING From BufferedReader
        String userSelection = terminalReader.readLine();

        // What's a switch statement?
        // If we hve many cases or conditions and well continue to evaluated until coditions is met based
        // on the user selection
        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                LoginMenu loginMenu = new LoginMenu(terminalReader);
                loginMenu.render();
                break;
            case "2":
                System.out.println("User has selected register...");
                RegisterMenu registerMenu = new RegisterMenu(terminalReader);
                registerMenu.render();
                break;
            case "3":
                System.out.println("User has selected update information...");
                UpdateMenu updateMenu = new UpdateMenu(terminalReader);
                updateMenu.render();
                break;
            case "4":
                System.out.println("User has selected create account...");
                System.out.println("===    m@.@m    === Under Construction ===          m@.@m        ===");
                // CreateMenu createMenu = new CreateMenu(terminalReader);
                // createMenu.render();
                break;
            case "5":
                System.out.println("User has selected view all transactions for user's account...");
                System.out.println("===    m@.@m    === Under Construction ===          m@.@m        ===");
                break;
            case "6":
                System.out.println("User has selected exit...");
                // shutdown application
                // exit 0
                shutdown();
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}
