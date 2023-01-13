package com.epam;

import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import com.epam.web.command.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Controller.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String address = Path.PAGE_ERROR;
        String commandName = req.getParameter("command");
        log.trace("command ==> " + commandName);
        Command command = CommandContainer.getCommand(commandName);
        try {
            address = command.execute(req, resp);
            log.trace("address =>" + address);
        } catch (DBException | ParseException e) {
            req.setAttribute("error", e);
        }
        log.debug("Controller_doGet finished");
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        log.debug("Controller_doPost starts");
        String address = Path.PAGE_ERROR;
        String commandName = req.getParameter("command");
        log.trace("command => " + commandName);
        Command command = CommandContainer.getCommand(commandName);
        try {
            address = command.execute(req, resp);
            log.trace("address => " + address);
        } catch (DBException | ParseException e) {
            req.setAttribute("error", e);
        }
        StringBuilder q = new StringBuilder(req.getContextPath());
        q.append(address);
        log.debug("Controller_doPost finished");
        resp.sendRedirect(q.toString());
    }
}
