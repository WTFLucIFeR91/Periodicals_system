<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/Error.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<form action="controller" method="post">
<input type="hidden" name="command" value="signup"/>
<div class="container" style="width: 500px">
<div class="container text-center">
   <h1><fmt:message key="Signup"/></h1>
</div>
<c:set var = "error" scope = "session" value ='<%=(String)session.getAttribute("errorMessage")%>'/>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Email_address'/></label>
          <c:set var = "email" scope = "session" value ='<%=(String)session.getAttribute("email")%>'/>
          <c:set var = "emailIsUsing" scope = "session" value ='<%=(String)session.getAttribute("emailIsUsing")%>'/>
          <input type="text" name="email" class="form-control"  value = ${email}>
           <c:if test="${emailIsUsing eq 'error_page_invalid_email_is_using'}">
            <label  class="form-label"><fmt:message key='${emailIsUsing}'/></label>
           </c:if>
           <c:if test="${error eq 'error_page_invalid_email'}">
            <label  class="form-label"><fmt:message key='${error}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
        <c:set var = "password" scope = "session" value ='<%=(String)session.getAttribute("password")%>'/>
          <label  class="form-label"><fmt:message key='Password'/></label>
          <input type="password" name="password" class="form-control" value = '${password}'  required/>
           <c:if test="${error eq 'error_page_invalid_password'}">
            <label  class="form-label"><fmt:message key='${error}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
        <c:set var = "first_name" scope = "session" value ='<%=(String)session.getAttribute("first_name")%>'/>
          <label  class="form-label"><fmt:message key='Login_page_name'/></label>
          <input type="text" name="first_name" class="form-control" value = '${first_name}'  required/>
           <c:if test="${error eq 'error_page_invalid_name'}">
            <label  class="form-label"><fmt:message key='${error}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
        <c:set var = "last_name" scope = "session" value ='<%=(String)session.getAttribute("last_name")%>'/>
          <label  class="form-label"><fmt:message key='Login_page_surname'/></label>
          <input type="text" name="last_name" class="form-control" value = '${last_name}'  required/>
           <c:if test="${error eq 'error_page_invalid_name'}">
            <label  class="form-label"><fmt:message key='${error}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
        <c:set var = "telephone" scope = "session" value ='<%=(String)session.getAttribute("telephone")%>'/>
          <label  class="form-label"><fmt:message key='Telephone'/></label>
          <input type="text" name="telephone" class="form-control" placeholder="0XX-XXX-XX-XX" value = '${telephone}'  required/>
          <c:if test="${error eq 'error_page_invalid_phone_number'}">
                      <label  class="form-label"><fmt:message key='${error}'/></label>
          </c:if>
        </div>
        <p class = "text text-center mb-1"><fmt:message key='DeliveryAddress'/></p>
        <div class="mb-0">
        <c:set var = "login_page_city" scope = "session" value ='<%=(String)session.getAttribute("login_page_city")%>'/>
          <label  class="form-label"><fmt:message key='Login_page_city'/></label>
          <input type="text" name="login_page_city" class="form-control" value = '${login_page_city}'  required/>
           <c:if test="${error eq 'error_page_invalid_string'}">
            <label  class="form-label"><fmt:message key='${error}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
        <c:set var = "login_page_street" scope = "session" value ='<%=(String)session.getAttribute("login_page_street")%>'/>
          <label  class="form-label"><fmt:message key='Login_page_street'/></label>
          <input type="text" name="login_page_street" class="form-control"  value = '${login_page_street}' required/>
           <c:if test="${error eq 'error_page_invalid_string'}">
            <label  class="form-label"><fmt:message key='${error}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
        <c:set var = "login_page_building" scope = "session" value ='<%=(String)session.getAttribute("login_page_building")%>'/>
          <label  class="form-label"><fmt:message key='Login_page_building'/></label>
          <input type="text" name="login_page_building" class="form-control"  value = '${login_page_building}' required/>
        </div>
        <div class="mb-0">
        <c:set var = "login_page_apartment" scope = "session" value ='<%=(String)session.getAttribute("login_page_apartment")%>'/>
          <label  class="form-label"><fmt:message key='Login_page_apartment'/></label>
          <input type="text" name="login_page_apartment" class="form-control" value = '${login_page_apartment}'  required/>
        </div>
        </br>
        <div class = "d-flex justify-content-center">
          <form class="edit-profile" action="controller" method="post">
              <input type="hidden" name="command" value="updateUser"/>
              <input type="submit" value="<fmt:message key='main_page.save'/>">
          </form>
          </div>
      </div>
      </form>