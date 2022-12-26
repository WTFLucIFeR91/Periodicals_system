<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doc type html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome Page</title>
    <link  rel="stylesheet" href="style\bootstrap.min.css" type="text/css">
  </head>
  <body>

<header>
 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
   <div class="container-fluid">
     <a class="navbar-brand" href="controller?command=mainPage"><fmt:message key='Periodicals_system'/></a>
     <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarCollapse">
       <ul class="navbar-nav me-auto mb-2 mb-md-0">
         <li class="nav-item">
           <a class="nav-link" href="controller?command=login"><fmt:message key='login'/></a>
         </li>
         <li class="nav-item">
           <a class="nav-link" href="controller?command=signup"><fmt:message key='signup'/></a>
         </li>
        </ul>
  <form action="changeLocale.jsp" method="POST" class="nav-item" >
    <label class = "center">
      <select name="locale" onchange='submit();'>
         <c:forEach items="${applicationScope.locales}" var="locale">
            <c:set var="selected"
              value="${locale.key == currentLocale ? 'selected' : '' }"/>
                <option value="${locale.key}" ${selected}>${locale.value}</option>
            </c:forEach>
      </select>
    </label>
  </form>


     </div>
   </div>
 </nav>
</header>
<main>
<div class="container">
     <p class="lead"><h3><fmt:message key='Periodicals'/></h3></p>
</div>
  <div class="album py-5 bg-light">
    <div class="container">
      <div class="text-bg">

        <li><strong><fmt:message key='welcome_message1'/></strong></li>
        <li><strong><fmt:message key='welcome_message2'/></strong></li>
        <li><strong><fmt:message key='welcome_message3'/></strong></li>
        <li><strong><fmt:message key='welcome_message4'/></strong></li>
        <li><strong><fmt:message key='welcome_message5'/></strong></li>
        <li><strong><fmt:message key='welcome_message6'/></strong></li>
      </div>
    </div>
  </div>

  <div class="album py-5 bg-light">
      <div class="container">
        <div class="text-bg">
          <li><strong><fmt:message key='welcome_message7'/></strong></li>
        </div>
      </div>
    </div>


<div class="container">
  <footer class="py-3 my-4">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Features</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Pricing</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
    </ul>
    <p class="text-center text-muted">© 2022 EPAM Final Task</p>
  </footer>
</div>
    <script src="js\bootstrap.bundle.min.js"></script>
  </body>
</html>
