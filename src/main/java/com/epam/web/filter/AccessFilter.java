package com.epam.web.filter;

import com.epam.dao.DaoFactory;
import com.epam.entity.Role;
import com.epam.entity.Status;
import com.epam.entity.User;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AccessFilter implements Filter {

    private static final Logger log = LogManager.getLogger(EncodingFilter.class);

    private static Map<Role , List<String>> accessMap = new ConcurrentHashMap<>();
    private static List<String> outOfControl = new CopyOnWriteArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("AccessFilter init");

        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.USER, asList(filterConfig.getInitParameter("user")));
        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));

        log.debug("AccessFilter init finished");
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("AccessFilter is starting  ");

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (checkAccess(req, servletResponse)){
            log.debug("access is allowed");
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            log.debug("access is denied");

            String errorMassage = "You do not have permission to access the requested resource";

            servletRequest.setAttribute("errorMassage", errorMassage);
            servletRequest.getRequestDispatcher(Path.PAGE_ERROR_ACCESS).forward(servletRequest,servletResponse);
        }
    }

    private boolean checkAccess(HttpServletRequest req, ServletResponse servletResponse) throws ServletException, IOException {
        log.debug("checkAccess  ..");

        String commandName = req.getParameter("command");
        log.trace("commandName ==> " + commandName);

        if(outOfControl.contains(commandName)){
            log.debug("this command does not require access");
            return true;
        }

        HttpSession session = req.getSession(false);

        if(session == null){
            log.debug("session is null");
            return false;
        }

        User user = (User) session.getAttribute("user");
        if (user == null){
            log.debug("user is null");
            return false;
        }


        if (commandName == null || commandName.isEmpty()){
            log.debug("command is null");
            return false;
        }

        try {
            user = DaoFactory.createUserDao().findUserByEmail(user.getEmail());
        } catch (DBException e) {
            req.setAttribute("error", e);
            req.getRequestDispatcher(Path.PAGE_ERROR).forward(req,servletResponse);
        }

        log.trace("user =>" + user);

        Role userRole = user.getRole();

        if (user.getStatus().getName().equals(Status.BANNED.getName())){
            log.debug("user is blocked");
            req.getRequestDispatcher(Path.PAGE_BLOCKED_USER).forward(req,servletResponse);
        }

        log.debug("user is not blocked");

        return accessMap.get(userRole).contains(commandName);

    }

    @Override
    public void destroy() {
        System.out.println("AccessFilter destroy...");
    }

    private List<String> asList(String str) {
        List<String > list = new CopyOnWriteArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
