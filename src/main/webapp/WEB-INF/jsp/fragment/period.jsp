<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>

<c:forEach items="${publicationsMap}" var="entry">
<section class="recomended pt-5">
      <h2 class="h2">${entry.key.name}</h2>
      </br>
      <div class="row">
         <c:forEach items="${entry.value}" var="value">
                      <div class="col-md-6 col-lg-4 col-xl-3">
                        <div class="product__main d-flex flex-column" style="height: 100%">
                          <div class="product__img-wrap d-flex justify-content-center">
                            <img class="product__img product__img--cut" src="resources/images/${value.titleImgLink}" alt="${value.name}">
                            </div>
                            <h5 class="product__header mt-3 " style="height: 45px; overflow:hidden;"><fmt:message key='Name'/> : ${value.name}</h5>
                            <h6> <fmt:message key='Description'/> : ${value.description}</h6>
                            <h6> <fmt:message key='Index'/> : ${value.index}</h6>
                            <p class="product__price mt-auto"><fmt:message key='Price'/> : ${value.price}</p>

                            <form class="sub1" action="controller"  method="get">
                                <input type="hidden" name="command" value="subscribe"/>
                                <input type="hidden" name="periodicalId" value="${value.index}"/>
                                <input class="btn btn-warning btn--small btn-warning"  type="submit" value="<fmt:message key='main_page.subscribe'/>">
                            </form>
                        </div>
                      </div>
         </c:forEach>
      </div>
<section>
<div class ="container text-center" >
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
     <c:if test="${currentPage2 != 1}">
      <a class="page-link" href="controller?command=mainPage&page2=${currentPage2 - 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
       </c:if>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
    <c:if test="${currentPage2 lt noOfPages2}">
      <a class="page-link" href="controller?command=mainPage&page2=${currentPage2 + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
      </c:if>
    </li>
  </ul>
</nav>
</div>
</section>
</c:forEach>
</section>





