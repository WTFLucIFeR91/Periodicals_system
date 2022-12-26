<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doc type html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login Page</title>
    <link  rel="stylesheet" href="style\bootstrap.min.css" type="text/css">
    <style>
    .gradient-custom-3 {
    /* fallback for old browsers */
    background: #84fab0;

    /* Chrome 10-25, Safari 5.1-6 */
    background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5));

    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    background: linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5))
    }
    .gradient-custom-4 {
    /* fallback for old browsers */
    background: #84fab0;

    /* Chrome 10-25, Safari 5.1-6 */
    background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1));

    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    background: linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1))
    }
    </style>
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




  <div class="album py-5 bg-light">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
          <div class="card" style="border-radius: 15px;">
            <div class="card-body p-5">
              <h2 class="text-uppercase text-center mb-5"><fmt:message key='login'/></h2>

              <form action="controller" method="post">
               <input type="hidden" name="command" value="login"/>
                <div class="form-outline mb-4">
                  <input type="email" name="email" class="form-control form-control-lg" required/>
                  <label class="form-label" ><fmt:message key='Email_address'/></label>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" name="password" class="form-control form-control-lg" required/>
                  <label class="form-label" ><fmt:message key='Password'/></label>
                </div>

                <div class="d-flex justify-content-center">
                  <button type="submit" class="btn btn-success btn-block btn-lg  text-body"><fmt:message key='login'/></button>
                </div>

              </form>
            </div>
          </div>
        </div>
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
