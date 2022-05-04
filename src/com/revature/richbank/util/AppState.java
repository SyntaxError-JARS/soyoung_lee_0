package com.revature.richbank.util;

import com.revature.richbank.menus.RegisterMenu;
import com.revature.richbank.menus.WelcomeMenu;
import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    //private final MenuRouter router;

    private WelcomeMenu welcomeMenu;

    private RegisterMenu registerMenu;

    // default constructor.
    public AppState() {
        System.out.println("AppState:: constructor.");

        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        CustomerService customerService = new CustomerService();

        //
        this.welcomeMenu = new WelcomeMenu(terminalReader, customerService);
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        CustomerService customerService = new CustomerService();

        WelcomeMenu welcomeMenu = new WelcomeMenu (terminalReader, customerService);
        RegisterMenu registerMenu = new RegisterMenu(terminalReader);

    }

    public void startup(){
        welcomMenu.render();
    }

    public void shutdown() {

    }
}
