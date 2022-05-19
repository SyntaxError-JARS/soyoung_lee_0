package com.revature.richbank.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public interface Authable {
    static boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("authCustomer") == null) {
            resp.getWriter().write("Unauthorized request - not log in as a resistered user.");
            resp.setStatus(401); // Unauthrized
            return false;
        }
        return true;
    }


    static String getPathInfo(HttpServletRequest req) throws NullPointerException {

        String path = "";
        try {
            String[] pathParts = req.getPathInfo().split("/");

            if (pathParts.length > 1)
                path =  pathParts[1];
        } catch (NullPointerException e) {
            return path;
        }
        return path;
    }

    static String getCurrencyFormat(double amount){

        // Create a new Locale
        Locale usa = new Locale("en", "US");
        // Create a Currency instance for the Locale
        Currency dollars = Currency.getInstance(usa);
        // Create a formatter given the Locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        // Format the Number into a Currency String
        return dollarFormat.format(amount);
    }
}
