<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-xl">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col"><fmt:message key='Email'/></th>
      <th scope="col"><fmt:message key='Role'/></th>
      <th scope="col"><fmt:message key='Status'/></th>
      <th scope="col"><fmt:message key='First_name'/></th>
      <th scope="col"><fmt:message key='Second_name'/></th>
      <th scope="col"><fmt:message key='Telephone'/></th>
      <th scope="col"><fmt:message key='Balance'/></th>
      <th scope="col"><fmt:message key='Actions'/></th>

    </tr>
  </thead>
<c:set var="count" value="0" scope="request" />
<c:forEach items="${users}" var="users" varStatus="count">
  <tbody>
    <tr>
      <th scope="row">${count.count}</th>
      <td>${users.email}</td>
      <td>${users.role}</td>
      <td>${users.status}</td>
      <td>${users.userDetails.firstName}</td>
      <td>${users.userDetails.lastName}</td>
      <td>${users.userDetails.telephone}</td>
      <td>${users.balance}</td>
      <td>
                <form class="users-block" action="controller" method="get">
                                <input type="hidden" name="command" value="block"/>
                                <input type="hidden" name="userEmail" value="${users.email}"/>
                                <p><input type="submit" value="<fmt:message key='Block'/>"></p>
                            </form>
                            <form class="users-unblock" action="controller" method="get">
                                <input type="hidden" name="command" value="unblock"/>
                                <input type="hidden" name="userEmail" value="${users.email}"/>
                                <p><input type="submit" value="<fmt:message key='Unblock'/>"></p>
                            </form>
      </td>
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
      <a class="page-link" href="controller?command=showUsers&page=${currentPage - 1}" aria-label="Previous">
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
    <li class="page-item "><a class="page-link" href="controller?command=showUsers&page=${i}">${i}</a></li>
        </c:otherwise>
        </c:choose>
    </c:forEach>
    <li class="page-item">
    <c:if test="${currentPage lt noOfPages}">
      <a class="page-link" href="controller?command=showUsers&page=${currentPage + 1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
      </c:if>
    </li>
  </ul>
</nav>
</div>
</section>
</div>