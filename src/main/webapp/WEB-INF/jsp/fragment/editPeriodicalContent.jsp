<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="${pageContext.request.contextPath}/controller?command=editPeriodical" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
<div class="container" style="width: 500px">
<div class="container text-center">
   <h1><fmt:message key='Edit'/></h1>
</div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Periodical_name'/> : ${publication.name} </label>
          <input type="text" name="publicationName" class="form-control" placeholder="${publication.name}" required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Publication_index'/> : ${publication.index} </label>
          <input type="text" name="publicationIndex" class="form-control" placeholder="${publication.index}"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Description'/> : ${publication.description}</label>
          <input type="text" name="publicationDescription" class="form-control" placeholder="${publication.description}" required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Language_of_publication'/> : ${publication.language}</label>
          <input type="text" name="publicationLanguage" class="form-control"  placeholder="${publication.language}" required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Price'/> : ${publication.price}</label>
          <input type="text" name="publicationPrice" class="form-control" placeholder="${publication.price}"  required/>
        </div>
        <div class="mb-0">
          <label  class="form-label"><fmt:message key='Topic'/> : ${publication.topic.name}</label>
          <input type="text" name="publicationTopic" class="form-control"  placeholder="${publication.topic.name}" required/>
        </div>
        <div class="mb-0">
               <input type="file" id="file" class="input-file" name="img" accept="image/*" size="50" alt="photo" pattern=".{1,150}">
          </div>
        </br>
        <label class="input-button" for="file"><fmt:message key="Download"/>:</label>
        <div class="mb-0">
            <div class="d-flex justify-content-center">
                <c:if test = "${newImg == null}">
                  <img class="" src="resources/images/${publication.titleImgLink}" width="175" height="230" alt="${publication.name}">
                </c:if>
                <c:if test = "${newImg != null}">
                  <img class="" src="resources/images/${newImg}" width="175" height="230" alt="${newImg}">
                </c:if>
            </div>
        </div>
        <div class = "d-flex justify-content-center">
              <input type="submit"   value="<fmt:message key='main_page.save'/>">
        </div>
</div>
</form>