package com.revature.richbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.models.Account;
import com.revature.richbank.services.AccountService;
import com.revature.richbank.util.logging.Logger;

import static com.revature.richbank.web.servlets.Authable.checkAuth;

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
            if(req.getParameter("account_id") != null){
                Account account = accountService.readById(req.getParameter("account_id"));
                String payload = mapper.writeValueAsString(account);
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


        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        }
}
