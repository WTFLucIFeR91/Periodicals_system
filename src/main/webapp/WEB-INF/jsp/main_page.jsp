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
        <form class="d-flex" action="controller" method="get">
           <input type="hidden" name="command" value="mainPage"/>
           <input type="hidden" name="isSearch" value="true"/>
             <input class="form-control me-2" type="search" name="search" required>
               <button class="btn btn-outline-success" action="submit" type="submit" value="<fmt:message key='main_page.search'/>" ><fmt:message key='main_page.search'/></button>
        </form>
     </div>
   </div>
 </nav>
</header>
<main>
<div class="album py-5 bg-light">
    <div class="album py-5 bg-light">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <c:forEach var="num" items="${publications}">
        <div class="col">
          <div class="card shadow-sm">
            <li class="image-additional d-flex justify-content-center" data-thumb="<? $link_admin_img; ?>">
              <img class="thumbnail" src="resources/images/${num.titleImgLink}" title="${num.titleImgLink}" alt="${num.titleImgLink}">
          </li>
            <div class="card-body">
              <p class="card-text">Індекс видання: ${num.index}</p>
              <p class="card-text">Тематика: ${num.topic.name}</p>
              <p class="card-text">Опис: ${num.description}</p>
              <p class="card-text">Мова видання: ${num.language}</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <form class="sub1" action="controller"  method="get">
                                                      <input type="hidden" name="command" value="subscribe"/>
                                                      <input type="hidden" name="periodicalId" value="${num.index}"/>
                                                      <input type="submit" value="<fmt:message key='main_page.subscribe'/>">
                                                  </form>
                </div>
                <small class="text-muted"> Price: ${num.price}</small>
              </div>
            </div>
          </div>
        </div>
        </c:forEach>
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
