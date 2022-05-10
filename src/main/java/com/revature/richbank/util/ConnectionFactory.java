package com.revature.richbank.util;



import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

/* TODO : Understand Design Pattern
-----------------------------------------
Design Pattern
-----------------------------------------
    Singleton Design Pattern

    - Creational Pattern
    - Restricts that only a single instance of this class can be made within the application.
    - Constructor can not be invoked from the outside of this class
            -> private
    - Eager or Lazy singleton
-----------------------------------------
    Factory Design Pattern

    - Creational Pattern
    - used to abstract way the creating/instantiation of this class
-----------------------------------------


1. Driver Check - getInstance()
2. get Instance for connection
3. get Connection

constructor
public static ConnectionFactory getInstance()
public Connection getConnection()


------------------------------------------
 */
public class ConnectionFactory {

    // TODO : instead Eager Singleton - we will have only one instance
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    // Using this for safety reason --- property files
    // property load() getProperty()
    private Properties prop = new Properties();


    //TODO: This is specially a Singleton design pattern because of the private default constructor
    private ConnectionFactory() {
        try {
            prop.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This static block checks the driver is connected
    static {

        // TODO: you should download the Maven postgresql driver
        //       save under resources directory and add it to the
        //       Project Structure.
        //      File > Project Structure > Module


        // TODO: Reflections!!! are just viewing a class
        // ---> check there's Driver we are looking for in library.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // we can import static when we need this other class
    // Need some way for out ConectionFactory to be obtained by other classes
    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    // java.sql.Connection
    // java.sql.DriverManager
    // Once we get instance() we are able to execute getConnection() to return a Connection to our database
    public Connection getConnection() {

        Connection conn = null;

        // default url will connect the public schema
        // String url = "jdbc:postgresql://localhost:5432/postgres";

        // TODO: WE NEED TO FIX THIS FOR SECURITY
        //String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=test_slee_p0";
        //String user = "postgres";
        //String password = "password";

        try {
            // conn = DriverManager.getConnection(url, user, password);
            conn = DriverManager.getConnection(prop.getProperty("url"),
                   prop.getProperty("user"),
                   prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
