package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.dao.UserDetailsDAO;
import com.epam.entity.Role;
import com.epam.entity.Status;
import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class SignupCommand implements Command {

    private static final Logger log = LogManager.getLogger(SignupCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("SignupCommand starts");

        String address = "signup.jsp";

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if(methodName.equals("GET")){
            return Path.PAGE_SIGN_UP;
        }

        String email = req.getParameter("email");
        log.trace("email => "+ email);

        String firstName = req.getParameter("first_name");
        log.trace("firstName => "+ firstName);

        String lastName = req.getParameter("last_name");
        log.trace("lastName => "+ firstName);

        String password = req.getParameter("password");
        log.trace("password => "+password);

        String city = req.getParameter("login_page_city");
        log.trace("city => "+city);

        String street = req.getParameter("login_page_street");
        log.trace("street => "+street);

        String building = req.getParameter("login_page_building");
        log.trace("building => "+building);

        String apartment = req.getParameter("login_page_apartment");
        log.trace("apartment => "+apartment);



        String deliveryAddress = city +
                street +","+
                building +","+
                apartment+".";

        User user = new User();
        UserDetails ud = new UserDetails();

        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setBalance(BigDecimal.valueOf(0.00));

        ud.setFirstName(firstName);
        ud.setLastName(lastName);
        ud.setDeliveryAddress(deliveryAddress);

        UserDetailsDAO userDetailsDAO = DaoFactory.createUserDetailsDao();
        UserDAO userDAO = DaoFactory.createUserDao();


        if(userDAO.addUser(user) && userDetailsDAO.addUserDetails(ud,user)){
            user = userDAO.findUserByEmail(email);
            HttpSession session = req.getSession();
            initSession(session,user);

            address = Path.COMMAND_CLIENT_PAGE;
        }

        log.debug("SignupCommand finished");

        return address;

    }

    private void initSession(HttpSession session, User user) {
        Role userRole = Role.getRole(user);
        log.trace("userRole => "+userRole);

        session.setAttribute("userRole", userRole);

        session.setAttribute("user",user);
        log.trace("user => "+user);
    }
}
