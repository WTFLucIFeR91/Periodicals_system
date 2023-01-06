package com.epam.web.command.user;

import com.epam.dao.DaoFactory;
import com.epam.entity.Publication;
import com.epam.entity.Subscription;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowSubscriptionsCommand implements Command {

    private static final Logger log = LogManager.getLogger(ShowSubscriptionsCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("ShowSubscriptionsCommand starts");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        log.trace("user => " + user);




        int page = 1;
        int recordsPerPage =6;
        int noOfRecords = DaoFactory.createSubscriptionDAO().count(user.getEmail());
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));


        List<Subscription> subscription = DaoFactory.createSubscriptionDAO().findSubscriptionsByUserEmail(user.getEmail(),(page - 1) * recordsPerPage,recordsPerPage);
        user.setSubscriptions(subscription);

        session.setAttribute("user",user);

        session.setAttribute("subscription", subscription);

        req.setAttribute("subscription", subscription);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);


        log.debug("ShowSubscriptionsCommand finished");

        return Path.PAGE_SUBSCRIPTIONS;
    }


}
