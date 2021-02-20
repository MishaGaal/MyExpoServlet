<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 12.02.2021
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<head>
    <meta charset="UTF-8">
    <title>View Stat</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="card-group py-5">
    <c:forEach items="${tickets}" var="ticket">
        <div class="card my-3">
            <div class="card-body">
                <p class="card-text">${ticket.title}</p>
                <p>sold:  <h5 class="card-title">
                    ${ticket.count}</h5> tickets</p>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="blocks/footer.jsp"/>
</body>
</html>