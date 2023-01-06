package com.epam.web.command.admin;

import com.epam.dao.DaoFactory;
import com.epam.entity.Status;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserCommand implements Command {

    private static final Logger log = LogManager.getLogger(UnblockUserCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("UnblockUserCommand starts");

        String userEmail = req.getParameter("userEmail");

        User user = DaoFactory.createUserDao().findUserByEmail(userEmail);
        log.trace(" user => " + user);
        user.setStatus(Status.ACTIVE);
        DaoFactory.createUserDao().updateUser(user);

        log.debug("UnblockUserCommand finished");
        return Path.COMMAND_PAGE_USERS;
    }
}
