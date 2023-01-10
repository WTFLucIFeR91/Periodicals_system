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
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Email_address'/></label>
          <input type="email" name="email" class="form-control" placeholder="${user.email} " id="email" required
          pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" required value="${requestScope.user.email}">
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Password'/></label>
          <input type="password" name="password" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_name'/></label>
          <input type="text" name="first_name" class="form-control" placeholder="${user.userDetails.firstName}" required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_surname'/></label>
          <input type="text" name="last_name" class="form-control" placeholder="${user.userDetails.lastName}"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Telephone'/></label>
          <input type="text" name="telephone" class="form-control" placeholder="${user.userDetails.telephone}"  required/>
        </div>
        <p class = "text text-center mb-1"><fmt:message key='DeliveryAddress'/></p>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='DeliveryAddress'/></label>
          <input type="text" name="deliveryAddress" class="form-control" placeholder="${user.userDetails.deliveryAddress}"  required/>
        </div>
        </br>
        <div class = "d-flex justify-content-center">
              <input type="submit" value="<fmt:message key='main_page.save'/>">
          </div>
      </div>
</form>