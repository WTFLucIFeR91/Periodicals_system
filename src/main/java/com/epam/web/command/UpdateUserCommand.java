package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.dao.UserDetailsDAO;
import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserCommand implements Command {

    private static final Logger log = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("UpdateUserCommand starts");
        String address = Path.COMMAND_CLIENT_PROFILE;

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_UPDATE_USER;
        }

        String email = req.getParameter("email");
        log.trace("email => " + email);
        System.out.println("email => " + email);

        String firstName = req.getParameter("first_name");
        log.trace("firstName => " + firstName);
        System.out.println("firstName => " + firstName);

        String lastName = req.getParameter("last_name");
        log.trace("lastName => " + lastName);
        System.out.println("lastName => " + lastName);

        String password = req.getParameter("password");
        log.trace("password  => " + password);

        String telephone = req.getParameter("telephone");
        log.trace("telephone  => " + telephone);

        String deliveryAddress = req.getParameter("deliveryAddress");
        log.trace("deliveryAddress  => " + deliveryAddress);

        if (email == null || email.isEmpty() || firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() || password == null || password.isEmpty()) {
            return Path.COMMAND_CLIENT_PROFILE;
        }

        User userFromSession = (User) req.getSession().getAttribute("user");// oldUser
        log.trace("user before update => " + userFromSession);

        User userUpdate = new User();//newUser
        userUpdate.setEmail(email);
        userUpdate.setPassword(password);

        UserDetails ud = new UserDetails();
        ud.setFirstName(firstName);
        ud.setLastName(lastName);
        ud.setTelephone(telephone);
        ud.setDeliveryAddress(deliveryAddress);
        userUpdate.setUserDetails(ud);

        UserDAO userDAO = DaoFactory.createUserDao();
        userDAO.updateUserByEmail(userFromSession.getEmail(), userUpdate);

        User user = userDAO.findUserByEmail(userUpdate.getEmail());

        user = userDAO.findUserByEmail(user.getEmail());
        log.trace("user after update => " + user);

        req.getSession().setAttribute("user", user);

        log.debug("UpdateUserCommand finished");
        return address;
    }
}
