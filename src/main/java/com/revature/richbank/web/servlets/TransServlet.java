package com.revature.richbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.richbank.models.Trans;
import com.revature.richbank.services.TransService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.revature.richbank.web.servlets.Authable.checkAuth;

public class TransServlet extends HttpServlet implements Authable {

    private final TransService transService;
    private final ObjectMapper mapper;

    public TransServlet(TransService transService, ObjectMapper mapper){
        this.transService = transService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;

        if(req.getParameter("trans_id") != null){
            Trans trans = transService.readById(req.getParameter("trans_id"));
            String payload = mapper.writeValueAsString(trans);
            resp.getWriter().write(payload);
            return;
        }

        if( req.getParameter("account_number") != null) {
            List<Trans> transList = transService.readAll(req.getParameter("account_number"));
            String payload = mapper.writeValueAsString(transList);
            resp.getWriter().write(payload);
            return;
        }

        List<Trans> transList = transService.readAll();
        String payload = mapper.writeValueAsString(transList);
        resp.getWriter().write(payload);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Trans trans = mapper.readValue(req.getInputStream(), Trans.class);

        Trans persistedTrans = transService.create(trans);

        String payload = mapper.writeValueAsString(persistedTrans);
        resp.getWriter().write(payload);
        resp.setStatus(201);    // success to create
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Trans trans = mapper.readValue(req.getInputStream(), Trans.class);
        Trans persistedTrans = transService.update(trans);

        String payload = mapper.writeValueAsString(persistedTrans);
        resp.getWriter().write(payload);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
