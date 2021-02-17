<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 06.02.2021
  Time: 11:24
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
    <title>Welcome!</title>
    <link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
</head>
<body class="container bg-light">
<jsp:include page="blocks/header.jsp"/>
<div class="container pt-5" id="wrapper">
    <h1 class="text-dark"><fmt:message key="expo"/></h1>
    <c:if test="${sessionScope.login != null}"> ${sessionScope.login}</c:if>
    <form id="signin" method="post" action="${pageContext.request.contextPath}/app/login">
        <div><input name="name" placeholder="username" type="text"/></div>
        <div><input name="pass" placeholder="password" type="password"/></div>
        <button type="submit">&#xf0da;</button>
    </form>
    <p>No account yet?<a href="${pageContext.request.contextPath}/app/registration">click here</a></p>
</div>
</body>
</html>
