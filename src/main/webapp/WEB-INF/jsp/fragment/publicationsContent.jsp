<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="recomended pt-5">
<div class="container-xl">
      <div class="row">
      <c:forEach items="${publications}" var="pub">
                      <div class="col-md-6 col-lg-6 col-xl-2">
                        <div class="d-flex flex-column" style="height: 100%">
                          <div class="d-flex justify-content-center">
                            <img class="" src="resources/images/${pub.titleImgLink}" alt="${pub.name}">
                            </div>
                            <h6><fmt:message key='Name'/> : ${pub.name}</h6>
                            <h7> <fmt:message key='Description'/> : ${pub.description}</h7>
                            <h7> <fmt:message key='Index'/> : ${pub.index}</h7>
                            <h7> <fmt:message key='Topic'/> : ${pub.topic.name}</h7>
                            <p class="mt-auto"><fmt:message key='Price'/> : ${pub.price}</p>

                            <form class="sub1" action="controller"  method="get">
                                <input type="hidden" name="command" value="subscribe"/>
                                <input type="hidden" name="periodicalId" value="${pub.index}"/>
                                <input class="btn btn-warning btn--small btn-warning"  type="submit" value="<fmt:message key='main_page.subscribe'/>">
                            </form>
                        </div>
                      </div>
                      </c:forEach>
      </div>
       </div>
<section>
<div class ="container text-center" >
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
     <c:if test="${currentPage != 1}">
      <a class="page-link" href="controller?command=mainPage&page=${currentPage - 1}&sortBy=${sortName}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
       </c:if>
    </li>
     <c:forEach begin="1" end="${noOfPages}" var="i">
         <c:choose>
            <c:when test="${currentPage eq i}">
    <li class="page-item active"><a class="page-link" href="#">${i}</a></li>
            </c:when>
        <c:otherwise>
    <li class="page-item "><a class="page-link" href="controller?command=mainPage&page=${i}&sortBy=${sortName}">${i}</a></li>
        </c:otherwise>
        </c:choose>
    </c:forEach>
    <li class="page-item">
    <c:if test="${currentPage lt noOfPages}">
      <a class="page-link" href="controller?command=mainPage&page=${currentPage + 1}&sortBy=${sortName}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
      </c:if>
    </li>
  </ul>
</nav>
</div>
</section>







