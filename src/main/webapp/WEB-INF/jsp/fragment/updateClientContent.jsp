<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<form action="controller" method="post">
<input type="hidden" name="command" value="updateUser"/>
<div class="container" style="width: 500px">
<div class="container text-center">
   <h1><fmt:message key="Edit_profile"/></h1>
</div>
<c:set var = "error" scope = "session" value ='<%=(String)session.getAttribute("errorMessage")%>'/>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Email_address'/></label>
          <c:set var = "emailIsUsing" scope = "session" value ='<%=(String)session.getAttribute("emailIsUsing")%>'/>
          <input disabled type="email" name="email" class="form-control " value="${user.email}"  id="email"  required />
                     <c:if test="${error eq 'error_page_invalid_email'}">
                      <label  class="form-label"><fmt:message key='${error}'/></label>
                     </c:if>
           <c:if test="${emailIsUsing eq 'error_page_invalid_email_is_using'}">
            <label  class="form-label"><fmt:message key='${emailIsUsing}'/></label>
           </c:if>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Password'/></label>
          <input type="password" name="password" class="form-control"  required/>
               <c:if test="${error eq 'error_page_invalid_password'}">
                      <label  class="form-label"><fmt:message key='${error}'/></label>
                     </c:if>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_name'/></label>
          <input type="text" name="first_name" class="form-control" value="${user.userDetails.firstName}" required/>
               <c:if test="${error eq 'error_page_invalid_name'}">
                <label  class="form-label"><fmt:message key='${error}'/></label>
               </c:if>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_surname'/></label>
          <input type="text" name="last_name" class="form-control" value="${user.userDetails.lastName}"  required/>
            <c:if test="${error eq 'error_page_invalid_name'}">
             <label  class="form-label"><fmt:message key='${error}'/></label>
            </c:if>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Telephone'/></label>
          <input type="text" name="telephone" class="form-control" value="${user.userDetails.telephone}"  required/>
                    <c:if test="${error eq 'error_page_invalid_phone_number'}">
                                <label  class="form-label"><fmt:message key='${error}'/></label>
                    </c:if>
        </div>
        <p class = "text text-center mb-1"><fmt:message key='DeliveryAddress'/></p>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='DeliveryAddress'/></label>
          <input type="text" name="deliveryAddress" class="form-control" value="${user.userDetails.deliveryAddress}"  required/>
                    <c:if test="${error eq 'error_page_invalid_text'}">
                                <label  class="form-label"><fmt:message key='${error}'/></label>
                    </c:if>
        </div>
        </br>
        <div class = "d-flex justify-content-center">
              <input type="submit" value="<fmt:message key='main_page.save'/>">
          </div>
      </div>
</form>