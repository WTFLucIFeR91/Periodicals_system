<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<header>
          <nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
              <div class="container-xl">
                <a class="navbar-brand" href="controller?command=mainPage"><fmt:message key='Periodicals_system'/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarsExample02">
                  <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                      <a class="nav-link active" aria-current="page" href="login.jsp"><fmt:message key='Login'/></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="signup.jsp"><fmt:message key='Signup'/></a>
                    </li>
                  </ul>
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                      <ul class="navbar-nav me-auto">
		                 <li class="nav-item">
                           <a class="nav-link active" style="margin-top: 15px;" aria-current="page" href="?sessionLocale=uk">UA</a>
                         </li>
                         <li class="nav-item">
                           <a class="nav-link active" style="margin-top: 15px;" aria-current="page" href="?sessionLocale=en">EN</a>
                         </li>
                      </ul>
                    <div>
                    </div>
                  </div>
                </div>
              </div>
            </nav>
      </header>
      </br>
