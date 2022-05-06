package com.revature.richbank.util;

import com.revature.richbank.menus.*;


import com.revature.richbank.services.CustomerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    //private final MenuRouter router;

    private WelcomeMenu welcomeMenu;

    private RegisterMenu registerMenu;

    private LoginMenu loginMenu;

    // default constructor.
    public AppState() {
        System.out.println("AppState::AppState() constructor.");

        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        CustomerService customerService = new CustomerService();

        // TODO: Why are we doing all of this?
        this.welcomeMenu = new WelcomeMenu(terminalReader, customerService);
        //this.registerMenu = new RegisterMenu(terminalReader);

    }

    public void startup(){
        System.out.println("AppState::startup() : ");
        try {
            while (isRunning) {
                System.out.println("AppState::startup() : Application successfully started!");
                //registerMenu.render();
                // comment in and out based on what you want to use
                welcomeMenu.render();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void shutdown() {
        isRunning = false;
        System.out.println("AppState::shutdown() : Application shutting down...");
    }
}
