package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.entity.Enum.Role;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.exceptions.IncorrectPasswordException;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.epam.util.PasswordHashUtil.verify;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);
    private  final String email = "email";
    private  final String password = "password";

    private User user;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("LoginCommand starts");
        HttpSession session = req.getSession();

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_LOGIN;
        }

        Map<String, String> parameterMap = getRequestParameters(req);
        initParameterMapToSession(session, parameterMap);

        String error = "errorMessage";
        if (isPresentEmailAndPassword(parameterMap)) {
            log.warn("Login or password cannot be empty");
            req.getSession().setAttribute(error, "Login_or_password_cannot_be_empty");
            return Path.PAGE_LOGIN;
        }

        try {
            UserDAO userDAO = DaoFactory.createUserDao();
            user = userDAO.findUserByEmail(parameterMap.get(email));
            log.trace("user => " + user);
        } catch (Exception e) {
            req.getSession().setAttribute(error, "Cant_find_user_by_email");
            return Path.PAGE_LOGIN;
        }

        try {
            isVer(parameterMap.get(password), user);
        } catch (Exception ex) {
            req.getSession().setAttribute(error, "error_pass_wrong");
            return Path.PAGE_LOGIN;
        }

        removeParamFromSession(parameterMap, session);
        initSession(session, user);

        log.debug("LoginCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }

    private boolean isPresentEmailAndPassword(Map<String, String> parameterMap) {
        return parameterMap.get(email) == null || parameterMap.get(password) == null ||
                parameterMap.get(password).isEmpty() || parameterMap.get(email).isEmpty();
    }

    private void initParameterMapToSession(HttpSession session, Map<String, String> parameterMap) {
        for (Map.Entry<String, String> pair : parameterMap.entrySet()) {
            session.setAttribute(pair.getKey(), pair.getValue());
        }
    }


    private static void isVer(String password, User user) {
        try {
            verify(user.getPassword(), password);
        } catch (IncorrectPasswordException e) {
            throw new RuntimeException(e);
        }
    }

    private void initSession(HttpSession session, User user) {
        Role userRole = Role.getRole(user);
        log.trace("userRole => " + userRole);

        session.setAttribute("userRole", userRole);

        session.setAttribute("user", user);
        log.trace("user => " + user);

    }

    private Map<String, String> getRequestParameters(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        map.put(email, req.getParameter(email));
        map.put(password, req.getParameter(password));
        return map;
    }

    private void removeParamFromSession(Map<String, String> parameterMap, HttpSession session) {
        for (Map.Entry<String, String> pair : parameterMap.entrySet()) {
            session.removeAttribute(pair.getKey());
        }
    }
}
