package com.revature.richbank.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO : give the path for this servlet
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    // This is displayed when you go to http://localhost:8080/test-app/test
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // ducking
        resp.getWriter().write("<h1>/This Test Servlet is working as intended for Rich Bank!!!</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
