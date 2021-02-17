<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 12.02.2021
  Time: 12:16
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
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="py-5 container my-10">
    <div class="card-columns">
        <c:forEach items="${users}" var="user">
            <div class="card text-center my-3">
                <div class="m-2">
                    <h5 class="card-title">${user.username}</h5>
                    <p class="card-text">${user.email}</p>
                    <p class="card-text">${user.roles}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
