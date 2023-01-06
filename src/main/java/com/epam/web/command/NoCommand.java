package com.epam.web.command;

import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {

    private static final Logger log = LogManager.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("Command starts");
        String errorMessage = "No such command";
        req.setAttribute("errorMessage", errorMessage);
        log.error("Set the request attribute: errorMessage -> " + errorMessage);

        log.debug("Command finished");

        log.error(errorMessage);
        throw new DBException(errorMessage);

    }
}

