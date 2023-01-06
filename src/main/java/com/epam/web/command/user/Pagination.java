package com.epam.web.command.user;

import com.epam.dao.DaoFactory;
import com.epam.entity.Publication;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Pagination implements Command {

    private static final Logger log = LogManager.getLogger(Pagination.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, ParseException {

        log.debug("Pagination starts");

        int page = 1;
        int recordsPerPage = 4;

        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<Publication> list = DaoFactory.createPublicationDAO().getPublication((page-1)*recordsPerPage,recordsPerPage);

        int noOfRecords = DaoFactory.createPublicationDAO().findAllPeriodicals().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            req.setAttribute("employeeList", list);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);


        try {
            req.getRequestDispatcher(Path.COMMAND_MAIN_PAGE).forward(req,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);

        }

        return Path.COMMAND_MAIN_PAGE;
        }

}
