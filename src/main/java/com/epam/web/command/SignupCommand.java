package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.dao.UserDetailsDAO;
import com.epam.entity.Enum.Role;
import com.epam.entity.Enum.Status;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SignupCommand implements Command {

    private static final Logger log = LogManager.getLogger(SignupCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("SignupCommand starts");

        HttpSession session = req.getSession();
        String address = "signup.jsp";

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_SIGN_UP;
        }

        Map<String, String> parameterMap = getRequestParameters(req, session);
        initParameterMapToSession(session, parameterMap);

        try {
            dataValidation(parameterMap);

            if (DaoFactory.createUserDao().emailIsUsing(parameterMap.get("email"))){
                req.getSession().setAttribute("emailIsUsing", "error_page_invalid_email_is_using");
                return Path.PAGE_SIGN_UP;
            }

        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            req.getSession().setAttribute("errorMessage", e.getMessage());
            return Path.PAGE_SIGN_UP;
        }

        removeParamFromSession(parameterMap,session);
        User user = buildUser(parameterMap);

        UserDetailsDAO userDetailsDAO = DaoFactory.createUserDetailsDao();
        UserDAO userDAO = DaoFactory.createUserDao();


        if (userDAO.addUser(user) && userDetailsDAO.addUserDetails(user.getUserDetails(), user)) {
            user = userDAO.findUserByEmail(parameterMap.get("email"));
            initSession(session, user);
            address = Path.COMMAND_MAIN_PAGE;
        }
        log.debug("SignupCommand finished");
        return address;

    }

    private static void dataValidation(Map<String, String> parameterMap) {
        DataValidator.validateEmail(parameterMap.get("email"));
        DataValidator.validatePassword(parameterMap.get("password"));
        DataValidator.validatePhoneNumber(parameterMap.get("telephone"));
        DataValidator.validateNames(parameterMap.get("first_name"));
        DataValidator.validateNames(parameterMap.get("last_name"));
        DataValidator.validateString(parameterMap.get("login_page_city"));
        DataValidator.validateString(parameterMap.get("login_page_street"));
        DataValidator.validateString(parameterMap.get("login_page_building"));
        DataValidator.validateString(parameterMap.get("login_page_apartment"));
    }


    private User buildUser(Map<String, String> parameterMap) {
        User user = new User();
        UserDetails ud = new UserDetails();

        user.setEmail(parameterMap.get("email"));
        user.setPassword(parameterMap.get("password"));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setBalance(BigDecimal.valueOf(0.00));

        ud.setFirstName(parameterMap.get("first_name"));
        ud.setLastName(parameterMap.get("last_name"));
        ud.setDeliveryAddress(parameterMap.get("deliveryAddress"));
        ud.setTelephone(parameterMap.get("telephone"));

        user.setUserDetails(ud);

        return user;

    }

    private void removeParamFromSession(Map<String, String> parameterMap,HttpSession session) {
        for (Map.Entry<String, String> pair: parameterMap.entrySet()) {
            session.removeAttribute(pair.getKey());
        }
    }

    private void initParameterMapToSession(HttpSession session, Map<String, String> parameterMap) {
        for (Map.Entry<String, String> pair: parameterMap.entrySet()) {
                session.setAttribute(pair.getKey(),pair.getValue());
        }
    }

    private void initSession(HttpSession session, User user) {
        Role userRole = Role.getRole(user);
        log.trace("userRole => " + userRole);
        session.setAttribute("userRole", userRole);
        session.setAttribute("user", user);
        log.trace("user => " + user);
    }

    private Map<String, String> getRequestParameters(HttpServletRequest req, HttpSession session) {
        Map<String, String> map = new HashMap<>();

        String email = req.getParameter("email");
        log.trace("email => " + email);
        map.put("email", email);

        String firstName = req.getParameter("first_name");
        log.trace("firstName => " + firstName);
        map.put("first_name", firstName);

        String lastName = req.getParameter("last_name");
        log.trace("lastName => " + firstName);
        map.put("last_name", lastName);

        String password = req.getParameter("password");
        log.trace("password => " + password);
        map.put("password", password);

        String telephone = req.getParameter("telephone");
        log.trace("telephone => " + telephone);
        map.put("telephone", telephone);

        String city = req.getParameter("login_page_city");
        log.trace("city => " + city);
        map.put("login_page_city", city);

        String street = req.getParameter("login_page_street");
        log.trace("street => " + street);
        map.put("login_page_street", street);

        String building = req.getParameter("login_page_building");
        log.trace("building => " + building);
        map.put("login_page_building", building);

        String apartment = req.getParameter("login_page_apartment");
        log.trace("apartment => " + apartment);
        map.put("login_page_apartment", apartment);

        String errorMessage = req.getParameter("errorMessage");
        log.trace("errorMessage => " + errorMessage);
        map.put("errorMessage", errorMessage);


        String emailIsUsing = req.getParameter("emailIsUsing");
        log.trace("emailIsUsing => " + emailIsUsing);
        map.put("emailIsUsing", emailIsUsing);

        String local = (String) session.getAttribute("lang");
        String deliveryAddress = "";

        if (local.equals("uk")) {
            deliveryAddress = "Місто " + city + ", " + "вулиця " + street + ", " + "будинок " + building + ", " + "квартира " + apartment + ".";
        } else {
            deliveryAddress = "City " + city + ", " + "street " + street + ", " + "building " + building + ", " + "apartment " + apartment + ".";
        }
        map.put("deliveryAddress", deliveryAddress);
        return map;
    }

}
