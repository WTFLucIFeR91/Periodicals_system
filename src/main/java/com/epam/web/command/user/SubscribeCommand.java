package com.epam.web.command.user;

import com.epam.dao.DaoFactory;
import com.epam.entity.SubscriptionPeriod;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubscribeCommand implements Command {

    private static final Logger log = LogManager.getLogger(SubscribeCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("SubscribeCommand starts");

        HttpSession session = req.getSession();

        String periodicalIndex = req.getParameter("periodicalId");
        log.trace("periodicalIndex => " + periodicalIndex);

        User user = (User) session.getAttribute("user");
        String email = user.getEmail();

        if (DaoFactory.createSubscriptionDAO().isSubscribed(email,periodicalIndex)){
            log.debug("user is already subscribed, forward to error page");
            return Path.PAGE_SUBSCRIBED_USER;
        }

        session.setAttribute("periodical",DaoFactory.createPublicationDAO().findPublicationByIndex(periodicalIndex));


        log.debug("SubscribeCommand finished");
        return Path.PAGE_SUBSCRIBE;
    }
}
