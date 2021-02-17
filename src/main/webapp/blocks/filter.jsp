<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 10.02.2021
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<nav class="navbar-expand ml-3 pt-1">
    <ul class="navbar-nav">
        <li class="nav-item mr-4">
            <div aria-label="Basic example" class="btn-group" role="group">
                <a class="btn btn-outline-secondary my-2 my-sm-2"
                   href="${pageContext.request.contextPath}/app/expos/desc"><fmt:message key="desc"/></a>
                <a class="btn btn-outline-secondary my-2 my-sm-2"
                   href="${pageContext.request.contextPath}/app/expos/asc"
                   type="button"><fmt:message key="asc"/></a>
            </div>
        </li>
        <form class="form-inline mr-4" method="post" action="${pageContext.request.contextPath}/app/expos/dates">
            <input class="form-control" name="startDate" type="date">
            <div class="d-inline mr-2 ml-2">
                <p class="text-uppercase font-weight-bold "> - </p>
            </div>
            <input class="form-control" name="endDate" type="date">
            <button class="btn btn-outline-success my-2 my-sm-2 ml-2" type="submit"><fmt:message key="filter"/></button>
        </form>
        <form class="form-inline  mr-4" method="post" action="${pageContext.request.contextPath}/app/expos/theme">
            <input aria-label="Search" class="form-control mr-sm-2" placeholder="Theme" name="theme"
                   type="search">
            <button class="btn btn-outline-success my-2 my-sm-2" type="submit"><fmt:message key="filter"/></button>
        </form>
    </ul>
</nav>
</html>
