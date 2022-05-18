package com.revature.richbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.dos.CustomerDao;
import com.revature.richbank.exceptions.ResourcePersistenceException;
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
public class CustomerServlet extends HttpServlet implements Authable {

    //private final CustomerService customerService = new CustomerService(new CustomerDao());
    private final CustomerService customerService;
    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();


    public CustomerServlet(CustomerService customerService, ObjectMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp))   return;

        logger.info("doGet() login_id : " + req.getParameter("login_id") );

        // String pathInfo = req.getPathInfo();
        // String[] pathParts = pathInfo.split("/");
        // System.out.println("req.getPathInfo() : " + pathParts[0] + " "  + pathParts[1]);


        /*
        if(req.getParameter("login_id") != null && req.getParameter("login_password") != null) {
            resp.getWriter().write("Your login_id: " + req.getParameter("login_id")  + " and password: " +req.getParameter("login_password"));
            return;
        }
*/
        if(req.getParameter("login_id") != null) {
            Customer customer;
            try {
                customer = customerService.readById(req.getParameter("login_id"));
            } catch (ResourcePersistenceException e) {
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }

            String payload = mapper.writeValueAsString(customer);
            resp.getWriter().write(payload);
            return;
        }

        List<Customer> customerList = customerService.readAll();
        String payload = mapper.writeValueAsString(customerList);
        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = mapper.readValue(req.getInputStream(), Customer.class);
        Customer persistedCustomer = customerService.create(customer);

        String payload = mapper.writeValueAsString(persistedCustomer);
        resp.getWriter().write(payload);
        resp.setStatus(201); //success to create
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp))   return;

        Customer customer = mapper.readValue(req.getInputStream(), Customer.class);
        Customer persistedCustomer = customerService.update(customer);

        String payload = mapper.writeValueAsString(persistedCustomer);
        resp.getWriter().write(payload);
        resp.setStatus(200); //success
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp))   return;

        Customer customer = mapper.readValue(req.getInputStream(), Customer.class);
        //Customer persistedCustomer = customerService.delete(customer.getLogin_id());
        if ( !customerService.delete(customer.getLogin_id()) ){
            resp.getWriter().write("Fail to delete customer.");
            resp.setStatus(401); // Unauthorized
            return;
        }

        String payload = mapper.writeValueAsString(customer);
        resp.getWriter().write(payload);
        //resp.getWriter().write("<h1>/Customer was deleted!!</h1>");
        resp.setStatus(200); //success
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
