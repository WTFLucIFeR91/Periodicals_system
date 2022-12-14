<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>



    <div class="container">
      <div class = "row d-flex justify-content-center align-items-center">
        <div class = "col-12 col-md-12 col-lg-12 col-xl-12">
          <div class = "card" style = "border-radius: 15px;">
             <div class = "card-body p-5">
                 <h2 class="text-center"><fmt:message key='Error-blocked_user'/></h2>
             </div>
          </div>
        </div>
      </div>
    </div>
