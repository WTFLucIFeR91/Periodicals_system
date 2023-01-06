<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
          <input type="email" name="email" class="form-control" placeholder="<fmt:message key='Email'/>"  required/>
        </div>
        <div class="mb-0">
          <div class="container text-center">
            <label  class="form-label"><fmt:message key='Password'/></label>
          </div>
          <input type="password" name="password" class="form-control" placeholder="<fmt:message key='Password'/>"  required/>
        </div>
</div>
</br>
</br>
<div class="container text-center">
<input type="submit" value="<fmt:message key='Login'/>"/>
</div>
</form>