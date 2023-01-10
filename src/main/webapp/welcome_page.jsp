<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>


<!DOC TYPE html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/WEB-INF/jsp/fragment/headerWelcomePage.jsp"/>
    <jsp:include page="/WEB-INF/jsp/fragment/welcomePageContent.jsp"/>
    <jsp:include page="/WEB-INF/jsp/fragment/footer.jsp"/>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>


</html>