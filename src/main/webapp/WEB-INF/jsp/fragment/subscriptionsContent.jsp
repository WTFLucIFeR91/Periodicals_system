<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-xl">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col"><fmt:message key='Index'/></th>
      <th scope="col"><fmt:message key='Periodical_name'/></th>
      <th scope="col"><fmt:message key='Status'/></th>
      <th scope="col"><fmt:message key='Active_from'/></th>
      <th scope="col"><fmt:message key='Active_to'/></th>
    </tr>
  </thead>
<c:set var="count" value="0" scope="request" />
<c:forEach items="${subscription}" var="subscription" varStatus="count">
  <tbody>
    <tr>
      <th scope="row">${count.count}</th>
      <td>${subscription.index}</td>
      <td>${subscription.publication.name}</td>
      <td>${subscription.status}</td>
      <td>${subscription.startDate}</td>
      <td>${subscription.endDate}</td>
    </tr>
</c:forEach>
  </tbody>
</table>
<section>
<div class ="container text-center" >
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
     <c:if test="${currentPage != 1}">
      <a class="page-link" href="controller?command=showSubscriptions&page=${currentPage - 1}" aria-label="Previous">
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
    <li class="page-item "><a class="page-link" href="controller?command=showSubscriptions&page=${i}">${i}</a></li>
        </c:otherwise>
        </c:choose>
    </c:forEach>
    <li class="page-item">
    <c:if test="${currentPage lt noOfPages}">
      <a class="page-link" href="controller?command=showSubscriptions&page=${currentPage + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
      </c:if>
    </li>
  </ul>
</nav>
</div>
</section>
</div>