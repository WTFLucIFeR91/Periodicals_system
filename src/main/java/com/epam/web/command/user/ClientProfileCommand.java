package com.epam.web.command.user;

import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientProfileCommand implements Command {

    private static final Logger log = LogManager.getLogger(ClientProfileCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("ClientProfileCommand starts");

        User user = (User) req.getSession().getAttribute("user");
        log.trace("User => " + user);

        log.debug("ClientProfileCommand finished");

        return Path.PAGE_CLIENT_PROFILE;
    }
}
