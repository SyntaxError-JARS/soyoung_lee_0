package com.revature.richbank.util.logging;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
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

    public static Logger getLogger(){
        if(logger == null)
            logger = new Logger(true);

        return logger;
    }

    // TODO : Check logging levels from IBM
    public void log(String message) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL file = loader.getResource("soyoung_lee_p0.log");

        //try(Writer logWriter = new FileWriter("src/main/resources/soyoung_lee_p0.log", true); ) {
        // Actual logging file --> C:\Program Files\apache-tomcat-9.0.62\webapps\soyoung_lee_p0\WEB-INF\classes

        System.out.println("Login url File:" + file );

        try(Writer logWriter = new FileWriter(String.valueOf(file).split(":")[1], true); ){

            logWriter.write(LocalDateTime.now() + "  LOG: " + message + "\n");
            logger.info("logger logger logger");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  LOG: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void info(String message) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL file = loader.getResource("soyoung_lee_p0.log");

        try(Writer logWriter = new FileWriter(String.valueOf(file).split(":")[1], true); ){
            logWriter.write(LocalDateTime.now() + "  LOG: " + message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  LOG: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void debug (String message) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL file = loader.getResource("soyoung_lee_p0.log");

        try(Writer logWriter = new FileWriter(String.valueOf(file).split(":")[1], true); ){
            logWriter.write(LocalDateTime.now() + "  LOG: " + message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  LOG: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void warn (String message) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL file = loader.getResource("soyoung_lee_p0.log");

        try(Writer logWriter = new FileWriter(String.valueOf(file).split(":")[1], true); ){
            logWriter.write(LocalDateTime.now() + "  LOG: " + message + "\n");

            if (printTOConsole)
                System.out.println(LocalDateTime.now() + "  LOG: " + message );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
