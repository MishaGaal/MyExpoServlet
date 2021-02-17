<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Tickets</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<div class="py-5 container my-10 mt-5">
    <div class="card-columns">

        <c:forEach items="${tickets}" var="ticket">
            <div class="card my-3 alert-warning">
                <div class="m-2">
                    <h5 class="card-title">${ticket.id}</h5>
                    <p class="card-text">${ticket.expo.title}</p>
                    <p class="card-text">${ticket.expo.startDate} - ${ticket.expo.endDate}</p>
                    <c:forEach items="${ticket.expo.holles}" var="hole">
                        <p class="card-text">${hole}</p>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
