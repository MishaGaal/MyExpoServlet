<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 06.02.2021
  Time: 11:26
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
    <jsp:include page="blocks/header.jsp"/>
    <title>Welcome</title>
    <link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" id="wrapper">
    <h1 class="text-dark"><fmt:message key="REGISTRATION"/></h1><br/>
    <form autocomplete="off" id="signin" method="post" action="${pageContext.request.contextPath}/app/registration">
        <div><input name="name" placeholder="username" type="text" value="${user.username}"/></div>
        <div><input name="email" placeholder="email" type="text" value="${user.email}"/></div>
        <div><input name="password" placeholder="password" type="password" value="${user.password}"/></div>
        <div><input name="passwordConf" placeholder="password" type="password" value="${passwordConf}"/></div>
        <c:if test="${sessionScope.valid != null}"> ${sessionScope.valid}</c:if>
        <button type="submit">&#xf0da;</button>
    </form>
</div>
</body>
</html>