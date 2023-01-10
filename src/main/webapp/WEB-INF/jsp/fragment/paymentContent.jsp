<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>


<input type="hidden" name="command" value="signup"/>
<div class="container" style="width: 500px">
         <div class="col">
             <div class="card shadow-sm">
                <li class="image-additional d-flex justify-content-center" data-thumb="<? $link_admin_img; ?>">
                   <img class="thumbnail" src="resources/images/${periodical.titleImgLink}" title="${periodical.titleImgLink}" alt="${num.titleImgLink}">
                </li>
         </div>
         <div class="card-body justify-content-center">
                <p class="text text-center mb-1"><fmt:message key='Name'/>: ${periodical.name}</p>
                  <p class="text text-center mb-1"><fmt:message key='Index'/> : ${periodical.index}</p>
                  <p class="text text-center mb-1"><fmt:message key='Topic'/> : ${periodical.topic.name}</p>
                  <p class="text text-center mb-1"><fmt:message key='Description'/> : ${periodical.description}</p>
                  <p class="text text-center mb-1"><fmt:message key='Language_of_publication'/> : ${periodical.language}</p>
                  <p class="text text-center mb-1"> <fmt:message key='Price'/> : ${periodical.price}</p>
                  <p class="text text-center mb-1"> <fmt:message key='Total_price'/>: ${periodical.price*subscriptionPeriod.number}</p>
         </div>

         </br>
         <div class="card-body justify-content-center text-center">
                        <form class="text-uppercase text-center mb-3" action="controller" method="get">
                          <input type="submit" value="<fmt:message key='Pay'/>">
                            <input type="hidden" name="command" value="paymentForm"/>
                         </form>
          </div>
</div>
</form>