<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doc type html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key='Client_page'/></title>
    <link  rel="stylesheet" href="style\bootstrap.min.css" type="text/css">
  </head>
  <body>
<header>
 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
   <div class="container-fluid">
     <a class="navbar-brand" href="controller?command=mainPage">${user.userDetails.firstName} ${user.userDetails.lastName}</a>
     <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarCollapse">
       <ul class="navbar-nav me-auto mb-2 mb-md-0">

         <li class="nav-item">
           <a class="nav-link" href="controller?command=clientPage"><fmt:message key='Periodicals'/></a>
         </li>
          <li class="nav-item">
                                      <a class="nav-link" href="controller?command=logout"><fmt:message key='logout'/></a>
                                    </li>
         <li class="nav-item">
         <form action="changeLocale.jsp" method="POST" class="nav-item" >
             <label class = "center">
               <select name="locale" onchange="submit();">
                  <c:forEach items="${applicationScope.locales}" var="locale">
                     <c:set var="selected"
                       value="${locale.key == currentLocale ? 'selected' : '' }"/>
                         <option value="${locale.key}" ${selected}>${locale.value}</option>
                     </c:forEach>
               </select>
             </label>
           </form>
           </li>

        </ul>
        <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
              </form>
     </div>
   </div>
 </nav>
</header>
<main>

<div class="album py-5 bg-light">
    <div class="container h-100">
    <div class = "row d-flex justify-content-center align-items-center h-100">
    <div class = "col-12 col-md-12 col-lg-12 col-xl-12">
    <div class = "card" style = "border-radius: 15px;">
    <div class = "card-body p-5">

<div class="bd-example-snippet bd-code-snippet"><div class="bd-example">
        <table class="table table-striped">
          <thead>
          <tr>
            <th scope="col"><fmt:message key='email'/></th>
            <th scope="col"><fmt:message key='Role'/></th>
            <th scope="col"><fmt:message key='status'/></th>
            <th scope="col"><fmt:message key='Balance'/></th>
            <th scope="col"><fmt:message key='telephone'/></th>
            <th scope="col"><fmt:message key='login_page_name'/></th>
            <th scope="col"><fmt:message key='login_page_surname'/></th>
            <th scope="col">Дії:</th>


          </tr>
          </thead>
          <c:forEach var="num" items="${users}">
          <tbody>
          <tr>
            <th scope="row">${num.email}</th>
            <td>${num.role}</td>
            <td>${num.status}</td>
            <td>${num.balance}</td>
            <td>${num.userDetails.telephone}</td>
            <td>${num.userDetails.firstName}</td>
            <td>${num.userDetails.lastName}</td>
            <td>
                <form class="users-block" action="controller" method="get">
                                <input type="hidden" name="command" value="block"/>
                                <input type="hidden" name="userEmail" value="${num.email}"/>
                                <p><input type="submit" value="<fmt:message key='block'/>"></p>
                            </form>
                            <form class="users-unblock" action="controller" method="get">
                                <input type="hidden" name="command" value="unblock"/>
                                <input type="hidden" name="userEmail" value="${num.email}"/>
                                <p><input type="submit" value="<fmt:message key='unblock'/>"></p>
                            </form>
            </td>

          </tr>
          </c:forEach>
          </tbody>
        </table>
        </div>
        </div>

</div>
</div>
</div>
</div>



</main>
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
