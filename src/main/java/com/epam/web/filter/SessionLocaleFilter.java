package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"},initParams = @WebInitParam(name = "defaultLocale",value = "en"))
public class SessionLocaleFilter implements Filter {
private String defaultLocale;

    @Override
    public void init(FilterConfig config){
        defaultLocale = config.getInitParameter("defaultLocale");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        }else {
            String locale = (String) req.getSession().getAttribute("lang");
            if(locale == null || locale.isEmpty()) {
                req.getSession().setAttribute("lang", defaultLocale);
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {}


}
