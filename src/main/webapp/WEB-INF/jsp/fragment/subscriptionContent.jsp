<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<input type="hidden" name="command" value="signup"/>
<div class="container" style="width: 500px">
         <div class="col">
             <div class="card shadow-sm">
                <li class="image-additional d-flex justify-content-center" data-thumb="<? $link_admin_img; ?>">
                   <img class="thumbnail" src="resources/images/${periodical.titleImgLink}" title="${periodical.titleImgLink}" alt="${num.titleImgLink}">
                </li>
         </div>
         <div class="card-body justify-content-center">
                <p class="text text-center mb-1">Назва: ${periodical.name}</p>
                  <p class="text text-center mb-1">Індекс видання: ${periodical.index}</p>
                  <p class="text text-center mb-1">Тематика: ${periodical.topic.name}</p>
                  <p class="text text-center mb-1">Опис: ${periodical.description}</p>
                  <p class="text text-center mb-1">Мова видання: ${periodical.language}</p>
                  <p class="text text-center mb-1"> Price: ${periodical.price}</p>
         </div>
         <div class="card-body justify-content-center text-center">
           <form action="controller" method="post">
               <th><fmt:message key='Subscription_period'/></th>
                  <select name="subscriptionType" >
                      <option selected value="ONE_MONTH"><fmt:message key='One_month'/></option>
                      <option value="THREE_MONTHS"><fmt:message key='Three_months'/></option>
                      <option value="SIX_MONTHS"><fmt:message key='Six_months'/></option>
                      <option value="ONE_YEAR"><fmt:message key='One_year'/></option>
                  </select>
         </div>
         </br>
         <div class="card-body justify-content-center text-center">
          <form class="text-uppercase text-center mb-3" action="controller" method="get">
                                     <input type="submit" value="<fmt:message key='Continue'/>">
                                     <input type="hidden" name="command" value="payment"/>
                                  </form>
          </div>
</div>
</form>