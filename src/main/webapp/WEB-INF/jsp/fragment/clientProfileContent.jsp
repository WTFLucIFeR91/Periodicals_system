<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<form action="controller" method="get">
    <div class="container" style="width: 350px">
      <div class="container text-center">
         <h1>${user.userDetails.firstName} ${user.userDetails.lastName}</h1>
      </div>
          <div class="container text-left">
            <label  class="form-label"><fmt:message key='Email'/>: ${user.email}</label></br>
            <label  class="form-label"><fmt:message key='Login_page_name'/>: ${user.userDetails.firstName}</label></br>
            <label  class="form-label"><fmt:message key='Login_page_surname'/>: ${user.userDetails.lastName}</label></br>
            <label  class="form-label"><fmt:message key='Telephone'/>: ${user.userDetails.telephone}</label></br>
            <label  class="form-label"><fmt:message key='DeliveryAddress'/>: ${user.userDetails.deliveryAddress}</label></br>
          </div>
    </div>
</br>
</br>
<div class="container text-center">
    <input type="hidden" name="command" value="updateUser"/>
      <input type="submit" value="<fmt:message key='Edit_profile'/>">
</div>
</form>