package com.revature.richbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.exceptions.AuthenticationException;
import com.revature.richbank.exceptions.InvalidRequestException;
import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;
import com.revature.richbank.web.dto.LoginCreds;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// TODO: Know about the HttpServlet abstract class,
//      This class has any abstract methods, its mean you don't need implement any method.

// TODO : give the path for this servlet
//      This annotation require a default No - Arg constructor
//      We will build listener
//@WebServlet("/auth")
public class AuthServlet extends HttpServlet {


    //private final CustomerService customerService = new CustomerService(new CustomerDao());
    //private static final CustomerService customerService;
    private final CustomerService customerService;

    // TODO: jackson-databind Java to Json content / Json to Java Object
    //      ObjectMapper - writeValue() method - Java to Jason
    //                     readValue() method = Jason to Java
    //
    private final ObjectMapper mapper;
    //private static final ObjectMapper mapper;


    public AuthServlet (CustomerService customerService, ObjectMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    // TODO: login process needs to be post
    //      because information is needed (id and password)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // TODO: Json to Java Object
            //       read all information from request body
            //       this information related with Customer
            //Customer reqCustomer = mapper.readValue(req.getInputStream(), Customer.class);
            //Customer authCustomer = customerService.authenticateCustomer(reqCustomer.getLogin_id(), reqCustomer.getLogin_password());
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);
            Customer authCustomer = customerService.authenticateCustomer(loginCreds.getLogin_id(), loginCreds.getLogin_password());


            // TODO: HttpSession - Store the information logged in (server: cookie)
            //       Response data
            HttpSession httpSession = req.getSession(true); // if session doesn't exist , then generate otherwise override current session
            // TODO set the attribute and get information from
            httpSession.setAttribute("authCustomer", authCustomer); // match the name with actual reference variable in Java


            //resp.setStatus(404);    // 200 default;;;
            resp.getWriter().write("You have successfully logged in");

        } catch (AuthenticationException | InvalidRequestException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(409);    // Conflict
            resp.getWriter().write(e.getMessage());
        }
    }
}
