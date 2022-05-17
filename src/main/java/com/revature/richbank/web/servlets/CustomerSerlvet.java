package com.revature.richbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.models.Customer;
import com.revature.richbank.services.CustomerService;
import com.revature.richbank.util.logging.Logger;

import static com.revature.richbank.web.servlets.Authable.checkAuth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebServlet("/customer")
public class CustomerSerlvet extends HttpServlet implements Authable {

    //private final CustomerService customerService = new CustomerService(new CustomerDao());
    private final CustomerService customerService;
    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();


    public CustomerSerlvet(CustomerService customerService, ObjectMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        /*
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authCustomer") == null) {
            resp.getWriter().write("Unauthorized request - not login in as registered user.");
            resp.setStatus(401); // Unauthorized
            return;
        }
         */
        //checkAuth(req, resp);
        if(!checkAuth(req, resp))   return;

        List<Customer> customerList = customerService.readAll();

        String payload = mapper.writeValueAsString(customerList);
        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp))   return;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp))   return;
    }

    // protected void checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // When you set up return type to void actually return nothing doesn't impact anything to the toGet code.
    /*
    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authCustomer") == null) {
            resp.getWriter().write("Unauthorized request - not login in as registered user.");
            resp.setStatus(401); // Unauthorized
            return false;
        }
        return true;
    }
     */
}
