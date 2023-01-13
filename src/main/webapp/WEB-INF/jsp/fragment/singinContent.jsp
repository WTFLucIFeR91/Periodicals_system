<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<form action="controller" method="post">
<input type="hidden" name="command" value="login"/>
<div class="container" style="width: 500px">
<div class="container text-center">
   <h1><fmt:message key="Login"/></h1>
</div>
        <div class="mb-0">
          <div class="container text-center">
            <label  class="form-label"><fmt:message key='Email'/></label>
          </div>
          <c:set var = "errorMessage" scope = "session" value ='<%=(String)session.getAttribute("errorMessage")%>'/>
          <c:set var = "email" scope = "session" value ='<%=(String)session.getAttribute("email")%>'/>
          <input type="email" name="email" class="form-control" value='${email}' required/>
                     <c:if test="${errorMessage eq 'Login_or_password_cannot_be_empty'}">
                      <label  class="form-label"><fmt:message key='${errorMessage}'/></label>
                     </c:if>
                     <c:if test="${errorMessage eq 'Cant_find_user_by_email'}">
                      <label  class="form-label"><fmt:message key='${errorMessage}'/></label>
                     </c:if>
        </div>
        <div class="mb-0">
          <div class="container text-center">
            <label  class="form-label"><fmt:message key='Password'/></label>
          </div>
          <c:set var = "errorMessage" scope = "session" value ='<%=(String)session.getAttribute("errorMessage")%>'/>
          <c:set var = "password" scope = "session" value ='<%=(String)session.getAttribute("password")%>'/>
          <input type="password" name="password" class="form-control" value='${password}'  required/>
                     <c:if test="${errorMessage eq 'error_pass_wrong'}">
                      <label  class="form-label"><fmt:message key='${errorMessage}'/></label>
                     </c:if>
        </div>
</div>
</br>
</br>
<div class="container text-center">
<input type="submit" value="<fmt:message key='Login'/>"/>
</div>
</form>