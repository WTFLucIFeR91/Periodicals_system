package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;
import com.epam.util.DataValidator;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.epam.util.PasswordHashUtil.encode;

public class UpdateUserCommand implements Command {

    private final Logger log = LogManager.getLogger(UpdateUserCommand.class);
    private final String email = "email";
    private final String firstName = "first_name";
    private final String lastName = "last_name";
    private final String password = "password";
    private final String telephone = "telephone";
    private final String deliveryAddress = "deliveryAddress";

    private User user;
    private User userUpdate;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("UpdateUserCommand starts");
        String address = Path.COMMAND_CLIENT_PROFILE;

        HttpSession session = req.getSession();

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_UPDATE_USER;
        }

        Map<String, String> parameterMap = getRequestParameters(req);
        initParameterMapToSession(session, parameterMap);


        if (isParameterPresent(parameterMap)) {
            return Path.COMMAND_UPDATE_USER;
        }

        try {
            dataValidation(parameterMap);
        } catch (Exception e) {
            session.setAttribute("errorMessage", e.getMessage());
            return Path.COMMAND_UPDATE_USER;
        }

        User userFromSession = (User) req.getSession().getAttribute("user");
        log.trace("user before update => " + userFromSession);


        userUpdate = buildUser(parameterMap, userFromSession);


        UserDAO userDAO = DaoFactory.createUserDao();
        userDAO.updateUser(userUpdate);

        user = userDAO.findUserByEmail(userUpdate.getEmail());

        log.trace("user after update => " + user);

        req.getSession().setAttribute("user", userUpdate);

        log.debug("UpdateUserCommand finished");
        removeParamFromSession(parameterMap, session);
        return address;
    }

    private User buildUser(Map<String, String> parameterMap, User userFromSession) {

        User userUpdate = new User();
        userUpdate.setEmail(userFromSession.getEmail());
        userUpdate.setPassword(encode(parameterMap.get(password)));
        userUpdate.setRole(userFromSession.getRole());
        userUpdate.setStatus(userFromSession.getStatus());
        userUpdate.setBalance(userFromSession.getBalance());

        UserDetails ud = new UserDetails();

        ud.setFirstName(parameterMap.get(firstName));
        ud.setLastName(parameterMap.get(lastName));
        ud.setTelephone(parameterMap.get(telephone));
        ud.setDeliveryAddress(parameterMap.get(deliveryAddress));
        userUpdate.setUserDetails(ud);
        return userUpdate;

    }

    private boolean isParameterPresent(Map<String, String> parameterMap) {
        return parameterMap.get(firstName) == null || parameterMap.get(firstName).isEmpty() ||
                parameterMap.get(lastName) == null || parameterMap.get(lastName).isEmpty() ||
                parameterMap.get(password) == null || parameterMap.get(password).isEmpty();
    }


    private Map<String, String> getRequestParameters(HttpServletRequest req) {

        Map<String, String> map = new HashMap<>();

        map.put(email, req.getParameter(email));
        map.put(firstName, req.getParameter(firstName));
        map.put(lastName, req.getParameter(lastName));
        map.put(password, req.getParameter(password));
        map.put(telephone, req.getParameter(telephone));
        map.put(deliveryAddress, req.getParameter(deliveryAddress));
        map.put("errorMessage", req.getParameter("errorMessage"));

        return map;
    }

    private void initParameterMapToSession(HttpSession session, Map<String, String> parameterMap) {
        for (Map.Entry<String, String> pair : parameterMap.entrySet()) {
            session.setAttribute(pair.getKey(), pair.getValue());
        }
    }

    private void removeParamFromSession(Map<String, String> parameterMap, HttpSession session) {
        for (Map.Entry<String, String> pair : parameterMap.entrySet()) {
            session.removeAttribute(pair.getKey());
        }
    }

    private void dataValidation(Map<String, String> parameterMap) {
        DataValidator.validatePassword(parameterMap.get(password));
        DataValidator.validatePhoneNumber(parameterMap.get(telephone));
        DataValidator.validateNames(parameterMap.get(firstName));
        DataValidator.validateNames(parameterMap.get(lastName));
        DataValidator.validateText(parameterMap.get(deliveryAddress));
    }
}
