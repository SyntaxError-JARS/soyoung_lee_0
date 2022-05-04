package com.revature.richbank.menus;

import java.io.BufferedReader;
import java.io.IOException;


public class WelcomeMenu extends Menu {

    private CustomerSerivce customerSerivce;

    public WelcomeMenu(BufferedReader terminalReader,  CustomerSerivce customerSerivce){
        super("Welcome Menu", "/welcome", terminalReader);
        this.customerSerivce = customerSerivce;
    }


    @Override
    public void render() throws Exception {
        String welcome = "Welcome to the Rich Bank!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) View/Create your account";
        String option4 = "4) View all transaction";
        String option5 = new String("5) Exit the Rich Bank"); // This is the same as ""


        // This is where we take the above variables and print them using a formatter
        // %s for STrings or %d for digits (numbers) in the string provided initially, then you provide the respective variables
        // println() is just invoking another method from out to add a new line after
        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();

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
                break;
            case "2":
                System.out.println("User has selected register...");
                //register(); // ctrl + left-click
                break;
            case "3":
                System.out.println("User has selected view/create account...");
                //pokemonInput(); // ctrl + left-click
                break;
            case "4":
                System.out.println("User has selected view transcations under user's account...");
                break;
            case "5":
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
