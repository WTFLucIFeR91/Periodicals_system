<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
          <nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
              <div class="container-xl">
                <a class="navbar-brand" href="controller?command=mainPage"><fmt:message key='Periodicals_system'/></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarsExample02">
                  <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                      <a class="nav-link disabled" aria-current="page" href="controller?command=login"><fmt:message key='Login'/></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link active" href="controller?command=signup"><fmt:message key='Signup'/></a>
                    </li>
                  </ul>
                  <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="First group">
                      <form class="d-flex" role="search" style="margin-top: 15px;">
                        <input class="form-control me-2" type="search" placeholder="<fmt:message key='Search'/>" disabled>
                        <button class="btn btn-outline-light disabled" type="submit"><fmt:message key='Search'/></button>
                      </form>
                    </div>
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                      <form  class="d-flex" action="changeLocale.jsp" method="POST"  >
                          <select class="form-select" style="margin-top: 15px;" name="locale" onchange="this.form.submit();">
                             <c:forEach items="${applicationScope.locales}" var="locale">
                                <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                                    <option value="${locale.key}" ${selected}>${locale.value}</option>
                                </c:forEach>
                          </select>

                      </form>
                    </div>
                    <div class="btn-group"  style="display: inline-flex;" role="group" aria-label="Third group">
                      <form class="d-flex" action="controller" method="get">
                        <select disabled class="form-select" style="margin-top: 15px;" name="sortBy" onchange="this.form.submit();">
                            <c:set var="selectedType" value="${sortName == 'type' ? 'selected' : '' }"/>
                            <c:set var="selectedName" value="${sortName == 'name' ? 'selected' : '' }"/>
                            <c:set var="selectedPrice" value="${sortName == 'price' ? 'selected' : '' }"/>
                            <option ${selectedType} value="type"><fmt:message key='Topic'/></option>
                            <option ${selectedName} value="name"><fmt:message key='Name'/></option>
                            <option ${selectedPrice} value="price"><fmt:message key='Price'/></option>
                        </select>
                    <input type="hidden" name="command" value="mainPage"/><br/>
                  </form>
                    </div>
                  </div>
                </div>
              </div>
            </nav>
      </header>
      </br>
      </br>
      </br>