package com.revature.richbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.exceptions.ResourcePersistenceException;
import com.revature.richbank.models.Account;
import com.revature.richbank.models.Customer;
import com.revature.richbank.services.AccountService;
import com.revature.richbank.util.logging.Logger;

import static com.revature.richbank.web.servlets.Authable.checkAuth;
import static com.revature.richbank.web.servlets.Authable.getPathInfo;
import static com.revature.richbank.web.servlets.Authable.getCurrencyFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AccountServlet extends HttpServlet implements Authable {

        private final AccountService accountService;
        private final ObjectMapper mapper;

        public AccountServlet(AccountService accountService, ObjectMapper mapper) {
            this.accountService = accountService;
            this.mapper = mapper;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            if (!checkAuth(req, resp)) return;


            if (req.getParameter("account_number") != null) {
                Account account = accountService.readById(req.getParameter("account_number"));
                String payload = mapper.writeValueAsString(account);


                if( getPathInfo(req).equals("balance")){
                    resp.getWriter().write("The balance of your account :" + account.getAccount_number()  +"\n");
                    String total = getCurrencyFormat(account.getTotal());
                    resp.getWriter().write(total + "\n\n" );
                }

                resp.getWriter().write(payload);
                return;
            }

            if (req.getParameter("customer_id") != null) {
                List<Account> accountList = accountService.readAll(req.getParameter("customer_id"));
                String payload = mapper.writeValueAsString(accountList);
                resp.getWriter().write(payload);
                return;
            }

            List<Account> accountList = accountService.readAll();
            String payload = mapper.writeValueAsString(accountList);

            resp.getWriter().write(payload);

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            if(!checkAuth(req, resp)) return;

            // TODO: Let's create a account
            Account newAccount = mapper.readValue(req.getInputStream(), Account.class); // from JSON to Java Object (Account)
            Account persistedAccount = accountService.create(newAccount);

            String payload = mapper.writeValueAsString(persistedAccount); // Mapping from Java Object (Account) to JSON

            resp.getWriter().write("Persisted the provided account as show below \n");
            resp.getWriter().write(payload);
            resp.setStatus(201);
        }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp))   return;

        Account account = mapper.readValue(req.getInputStream(), Account.class);
        Account persistedAccount = accountService.update(account);

        String payload = mapper.writeValueAsString(persistedAccount);
        resp.getWriter().write(payload);
        resp.setStatus(200); //success
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp))   return;

        Account account = mapper.readValue(req.getInputStream(), Account.class);
        if ( !accountService.delete(account.getAccount_number()) ){
            resp.getWriter().write("Fail to delete account.");
            resp.setStatus(401); // Unauthorized
            return;
        }

        String payload = mapper.writeValueAsString(account);
        resp.getWriter().write(payload);
        //resp.getWriter().write("<h1>/Account was deleted!!</h1>");
        resp.setStatus(200); //success
    }
}
