package com.revature.richbank.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.dos.AccountDao;
import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.dos.TransDao;
import com.revature.richbank.models.Customer;
import com.revature.richbank.models.Trans;
import com.revature.richbank.services.AccountService;
import com.revature.richbank.services.CustomerService;
import com.revature.richbank.services.TransService;
import com.revature.richbank.web.servlets.AccountServlet;
import com.revature.richbank.web.servlets.AuthServlet;
import com.revature.richbank.web.servlets.CustomerSerlvet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

// TODO: This listens all of surveillance and build out the context for them and
//      what dependency they need
//      what are they mapping
//      how to hit endpoints right

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Make a single ObjectMapper instance.
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first.
        CustomerDao customerDao = new CustomerDao();
        AccountDao accountDao = new AccountDao();
        TransDao transDao = new TransDao();

        // Instantiate and initialize the services  with their doo dependency.
        CustomerService customerService = new CustomerService(customerDao);
        AccountService accountService = new AccountService(accountDao);
        TransService transService = new TransService(transDao);

        AuthServlet authServlet = new AuthServlet(customerService, mapper);
        CustomerSerlvet customerSerlvet = new CustomerSerlvet(customerService, mapper);
        AccountServlet accountServlet = new AccountServlet(accountService, mapper);
        TransServlet transServlet = new TransServlet(transService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth"); // match with actual Servlet name
        // TODO : allow to us look at every after the customer path as well
        //      multiple end points
        context.addServlet("CustomerServlet", customerSerlvet).addMapping("/customer/*");
        context.addServlet("AccountServlet", accountServlet).addMapping("/account/*");
        context.addServlet("TransServlet", transServlet).addMapping("/trans/*");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
