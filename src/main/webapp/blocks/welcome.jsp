<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 10.02.2021
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<html>
<head>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container text-center bg-light mt-5">
    <div class="row py-lg-5">
        <div class="col-lg-6 col-md-8 mx-auto">
            <h1><fmt:message key="welcome"/></h1>
            <p class="lead text-muted"><fmt:message key="expo.text"/></p>
            <p>
                <a class="btn btn-success" href="${pageContext.request.contextPath}/app/main"><fmt:message
                        key="sign.in"/></a>
            </p>
        </div>
    </div>
</div>

</body>
</html>
