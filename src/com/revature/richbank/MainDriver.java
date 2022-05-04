package com.revature.richbank; // determining the package structure for the output

// importing the class from other packages to be leveraged
import com.revature.pokedex.models.Pokemon;
import com.revature.pokedex.models.Trainer;

// importing everything from java.io.
// this is a library that's provided by....JRE
import java.io.*;

// What's this?
// Creating a class called MainDriver
public class MainDriver {

    public static void main(String[] str) {

        System.out.println("MainDriver::main() 1. AppState instantiated");
        AppState appState = new AppState();


        System.out.println("MainDriver::main() 2. Application Startup");
        appState.startup();
    }

}


