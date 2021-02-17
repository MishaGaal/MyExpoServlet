<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 06.02.2021
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MYEXPO</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container-fluid bg-light">
<jsp:include page="blocks/header.jsp"/>
<fmt:message key="hello"/> ${user}!>
<div class="album mt-5 bg-light justify-content-between">
    <jsp:include page="blocks/expos.jsp"/>
</div>
<jsp:include page="blocks/pager.jsp"/>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
