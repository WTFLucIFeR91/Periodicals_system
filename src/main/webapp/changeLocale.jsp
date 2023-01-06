<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resources"/>

<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<%-- goto back to the settings--%>
<jsp:forward page="/controller?command=mainPage"/>

