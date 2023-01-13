<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>


<section>
<form action="controller" method="post">
 <input type="hidden" name="command" value="topUpBalance">
  <div class="container" style="width: 500px">
   <div class="container text-center">
    <h1><fmt:message key='Account_top-up'/></h1>
   </div>
     <div class="mb-0">
       <div class="container text-center">
       <c:set var = "error" scope = "session" value ='<%=(String)session.getAttribute("errorMessage")%>'/>
          <label class="form-label"><fmt:message key='Amount'/></label>
       </div>
       <input type="text" name="amount" class="form-control text-center" required/>
        <c:if test="${error eq 'error_page_invalid_number'}">
                             <label  class="form-label"><fmt:message key='${error}'/></label>
                            </c:if>
     </div>
     <div class="mb-0">
        <div class="container text-center">
           <label class="form-label"><fmt:message key='Card_number'/></label>
        </div>
        <input type="text" name="cardNumber" class="form-control text-center" placeholder="XXXX XXXX XXXX XXXX" value="4145 3312 4315 9843" required/>
      </div>
      <div class="mb-0">
        <div class="container text-center">
           <label class="form-label"><fmt:message key='Expiration'/></label>
        </div>
        <input type="text" name="validity" class="form-control text-center" placeholder="XX/XX" value="21/07" required/>
      </div>
      <div class="mb-0">
        <div class="container text-center">
           <label class="form-label">CVV2</label>
        </div>
        <input type="text" name="CVV2" class="form-control text-center" placeholder="XXX" value="502" required/>
      </div>
    </br>
      <div class="mb-0">
        <div class="container text-center">
            <input type="submit" value="<fmt:message key='Top_up'/>"/>
        </div>
      </div>
    </div>
</form>
</section>
