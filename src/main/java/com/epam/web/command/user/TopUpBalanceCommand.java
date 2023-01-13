package com.epam.web.command.user;

import com.epam.dao.DaoFactory;
import com.epam.entity.Enum.Role;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.util.DataValidator;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {

    private static final Logger log = LogManager.getLogger(TopUpBalanceCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("TopUpBalanceCommand starts");

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_TOP_UP_BALANCE;
        }

        HttpSession session = req.getSession();

        BigDecimal amount = new BigDecimal(req.getParameter("amount"));
        log.trace("amount => " + amount);
        try {
            DataValidator.validateMoneyAmount(req.getParameter("amount"));
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            req.getSession().setAttribute("errorMessage", e.getMessage());
            return Path.COMMAND_UPDATE_BALANCE;
        }
        User user = (User) session.getAttribute("user");
        log.trace("user => " + user);
        user.setBalance(user.getBalance().add(amount));
        DaoFactory.createUserDao().updateUser(user);
        session.setAttribute("user", user);
        log.debug("TopUpBalanceCommand finished");
        session.removeAttribute("errorMessage");
        return Path.COMMAND_MAIN_PAGE;
    }
}
