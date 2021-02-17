<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Expos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <jsp:include page="blocks/header.jsp"/>
</head>
<body class="container-fluid bg-light mt-9">
<div class="py-5 container my-5">
    <div class="d-flex justify-content-center">
        <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/app/expo/add"><fmt:message
                key="add"/></a>
        <a class="btn btn-warning btn-lg" href="${pageContext.request.contextPath}/app/stat"><fmt:message
                key="stat"/></a>
    </div>
    <div class="card-columns mt-10">
        <c:forEach items="${pages.expos}" var="expo">
            <div class="card text-center my-3">
                <img alt="Card image cap" class="card-img-top" height="225"
                     src="${pageContext.request.contextPath}/images/${expo.imgName}" width="300">
                <div class="m-2">
                    <h5 class="card-title">${expo.title}</h5>
                    <h5 class="card-title">${expo.titleUa}</h5>
                    <p class="card-text">${expo.description}</p>
                    <p class="card-text">${expo.startDate} - ${expo.endDate}</p>
                    <p class="card-text">${expo.descriptionUa}</p>
                    <p class="card-text">${expo.ticketPrice}</p>
                    <p class="card-text">${expo.amount}</p>
                    <p class="card-text">${expo.exhibited}</p>
                </div>
                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/app/expo/edit/${expo.id}"><fmt:message key="edit"/></a>
                <a class="btn btn-danger"
                   href="${pageContext.request.contextPath}/app/expo/delete/${expo.id}"><fmt:message key="delete"/></a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
<jsp:include page="blocks/pager.jsp"/>
<jsp:include page="blocks/footer.jsp"/>
</html>
