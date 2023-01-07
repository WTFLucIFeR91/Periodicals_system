package com.epam.web.command.admin;

import com.epam.dao.DaoFactory;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowUsersCommand implements Command {

    private static final Logger log = LogManager.getLogger(ShowUsersCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("ShowUsersCommand start");

        HttpSession session = req.getSession();

        int page = 1;
        int recordsPerPage =4;

        int noOfRecords = DaoFactory.createUserDao().countUsers();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));


        List<User> userList = DaoFactory.createUserDao().findAllUsersForPaginate((page - 1) * recordsPerPage,recordsPerPage);


        session.setAttribute("users", userList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        log.debug("ShowUsersCommand finished");
        return Path.PAGE_USERS;
    }
}
