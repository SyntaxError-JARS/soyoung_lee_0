package com.revature.richbank.util.logging;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.rmi.runtime.Log;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {

    private static Logger logger;

    private final boolean printTOConsole;   // TODO: check how works this : this is constant not able to change

    private Logger (boolean printTOConsole) {
        this.printTOConsole = printTOConsole;
    }

    // TODO : This is Lazy singleton - Figure out what is it.
    //        This logger is being lazily instantiated.
    //
    public static Logger getLogger(boolean printTOConsole){
        if(logger == null)
            logger = new Logger(printTOConsole);

        return logger;
    }

    // TODO : Check logging levels from IBM
    public void log(String message) {
        try(Writer logWriter = new FileWriter("src/main/resources/soyoung_lee_p0.log", true); ) {
            logWriter.write(message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  LOG: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void info(String message) {
        try(Writer logWriter = new FileWriter("src/main/resources/soyoung_lee_p0.log", true); ) {
            logWriter.write(message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  INFO: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void debug (String message) {
        try(Writer logWriter = new FileWriter("src/main/resources/soyoung_lee_p0.log", true); ) {
            logWriter.write(message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  DEBUG: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void warn (String message) {
        try(Writer logWriter = new FileWriter("src/main/resources/soyoung_lee_p0.log", true); ) {
            logWriter.write(message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  WARN: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
