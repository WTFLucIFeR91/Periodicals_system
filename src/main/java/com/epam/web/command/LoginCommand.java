package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command{

    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
       log.debug("LoginCommand starts");
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        log.trace("email => "+ email);

        String password = req.getParameter("password");
        log.trace("password => "+password);



        if (email == null || password == null || email.isEmpty() || password.isEmpty()){
            log.warn("Login or password cannot be empty");
            return Path.PAGE_LOGIN;
        }

        UserDAO userDAO = DaoFactory.createUserDao();
        User user = userDAO.findUserByEmail(email);
        log.trace("user => "+ user);

        if (user == null || !password.equals(user.getPassword())){
            log.warn("user is missing or wrong password");
            return Path.PAGE_LOGIN;
        }

        initSession(session,user);

        log.debug("LoginCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }

    private void initSession(HttpSession session, User user) {
        Role userRole = Role.getRole(user);
        log.trace("userRole => "+userRole);

        session.setAttribute("userRole", userRole);

        session.setAttribute("user",user);
        log.trace("user => "+user);

    }
}
