package com.epam.web.command.admin;

import com.epam.dao.DaoFactory;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePeriodical implements Command {
    private static final Logger log = LogManager.getLogger(DeletePeriodical.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("DeletePeriodicalCommand starts");

        String publicationIndex = req.getParameter("publicationIndex");
        log.trace("publicationIndex ==> " + publicationIndex);

        DaoFactory.createPublicationDAO().deletePublicationByIndex(publicationIndex);

        log.debug("DeletePeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }
}
