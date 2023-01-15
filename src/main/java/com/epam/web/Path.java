package com.epam.web;

public final class Path {
    public static final String PAGE_SIGN_UP = "/signup.jsp";
    public static final String PAGE_ERROR = "/WEB-INF/jsp/error/error.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_EDIT_PERIODICAL = "/WEB-INF/jsp/admin/edit_periodical.jsp";
    public static final String PAGE_ERROR_ACCESS = "/WEB-INF/jsp/error/error_access.jsp";
    public static final String PAGE_BLOCKED_USER = "/WEB-INF/jsp/error/blocked_user.jsp";
    public static final String PAGE_MAIN = "/WEB-INF/jsp/main_page.jsp";
    public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/admin_page.jsp";
    public static final String PAGE_ADMIN_PROFILE = "/WEB-INF/jsp/admin/admin_profile.jsp";
    public static final String PAGE_USERS = "/WEB-INF/jsp/admin/users.jsp";
    public static final String PAGE_SUBSCRIPTIONS = "/WEB-INF/jsp/client/subscriptions.jsp";
    public static final String PAGE_SUBSCRIBED_USER = "/WEB-INF/jsp/error/subscribed_user.jsp";
    public static final String PAGE_SUBSCRIBE = "/WEB-INF/jsp/client/subscribe.jsp";
    public static final String PAGE_PAYMENT = "/WEB-INF/jsp/client/payment.jsp";
    public static final String PAGE_CLIENT = "/WEB-INF/jsp/client/client_page.jsp";
    public static final String PAGE_CLIENT_PROFILE = "/WEB-INF/jsp/client/client_profile.jsp";
    public static final String PAGE_UPDATE_USER = "/WEB-INF/jsp/client/update_user.jsp";
    public static final String PAGE_ADD_PERIODICAL = "/WEB-INF/jsp/admin/add_periodical.jsp";
    public static final String PAGE_TOP_UP_BALANCE = "/WEB-INF/jsp/client/top_up_balance.jsp";
    public static final String COMMAND_CLIENT_PROFILE = "/controller?command=clientProfile";
    public static final String COMMAND_ADD_PERIODICAL = "/controller?command=addPeriodical";
    public static final String COMMAND_PAGE_USERS = "/controller?command=showUsers";
    public static final String COMMAND_PAYMENT = "/controller?command=payment";
     public static final String COMMAND_MAIN_PAGE = "/controller?command=mainPage";
    public static final String COMMAND_ADMIN_PAGE = "/controller?command=adminPage";
    public static final String COMMAND_CLIENT_PAGE = "/controller?command=clientPage";
     public static final String COMMAND_UPDATE_USER = "/controller?command=updateUser";
    public static final String COMMAND_UPDATE_BALANCE = "/controller?command=topUpBalance";
    public static final String COMMAND_EDIT_PERIODICAL_CONTROLLER = "/controller?command=editPeriodical&publicationIndex=";

    private Path() {
    }
}
