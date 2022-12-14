<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>
<!DOC TYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
   <jsp:include page="/WEB-INF/jsp/fragment/header.jsp"/>
    <jsp:include page="/WEB-INF/jsp/fragment/errorAccessContent.jsp"/>
    <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    <script src="/WEB-INF/jsp/js/bootstrap.bundle.min.js"></script>
</body>
</html>