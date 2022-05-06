package com.revature.richbank.util;

import com.revature.richbank.menus.*;


import com.revature.richbank.services.CustomerService;
import com.revature.richbank.util.logging.Logger;
import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    //private final MenuRouter router;

    private WelcomeMenu welcomeMenu;

    private RegisterMenu registerMenu;

    private final Logger logger;

    // default constructor.
    public AppState() {
        System.out.println("AppState::AppState() constructor.");

        // TODO : check if you give true what's happening here!
        // logger = Logger.getLogger(true); // print to terminal Console too.
        logger = Logger.getLogger(true);
        logger.log("AppState::startup() : Application successfully started!");

        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        CustomerService customerService = new CustomerService();

        this.welcomeMenu = new WelcomeMenu(terminalReader, customerService, logger);



    }


    public void startup(){
        try {
            while(isRunning) {
                logger.info("Application successfully started");
                // registerMenu.render();
                welcomeMenu.render(); // comment in and out based on what you want to use
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        isRunning = false;
        //logger.info("AppState::shutdown() : Application shutting down...");
        //logger = Logger.getLogger(false);
        System.out.println("AppState::shutdown() : Application shutting down...");
    }
}
