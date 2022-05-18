package com.revature.richbank.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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


    static String getPathInfo(HttpServletRequest req) {
        String[] pathParts = req.getPathInfo().split("/");
        return pathParts[1];
    }
}
