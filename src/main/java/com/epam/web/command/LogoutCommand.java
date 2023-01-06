package com.epam.web.command;

import com.epam.exceptions.DBException;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final Logger log = LogManager.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("LogoutCommand starts");

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return Path.PAGE_LOGIN;
    }
}
