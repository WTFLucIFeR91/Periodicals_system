<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="controller" method="post">
<input type="hidden" name="command" value="signup"/>
<div class="container" style="width: 500px">
<div class="container text-center">
   <h1><fmt:message key="Signup"/></h1>
</div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Email_address'/></label>
          <input type="email" name="email" class="form-control" placeholder="name@example.com" id="email" required
          pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" required value="${requestScope.user.email}">
            <c:if test= "${fn:contains(error, 'email')}">
            <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
          </c:if>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Password'/></label>
          <input type="password" name="password" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_name'/></label>
          <input type="text" name="first_name" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_surname'/></label>
          <input type="text" name="last_name" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Telephone'/></label>
          <input type="text" name="telephone" class="form-control"  required/>
        </div>
        <p class = "text text-center mb-1"><fmt:message key='DeliveryAddress'/></p>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_city'/></label>
          <input type="text" name="login_page_city" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_street'/></label>
          <input type="text" name="login_page_street" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_building'/></label>
          <input type="text" name="login_page_building" class="form-control"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Login_page_apartment'/></label>
          <input type="text" name="login_page_apartment" class="form-control"  required/>
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