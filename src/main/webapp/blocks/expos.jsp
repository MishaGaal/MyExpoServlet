<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 10.02.2021
  Time: 11:56
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
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <jsp:include page="filter.jsp"/>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">
        <c:forEach items="${pages.expos}" var="expo">
            <div class="card shadow-sm">
                <img alt="Card image cap" class="card-img-top" height="225"
                     src="${pageContext.request.contextPath}/images/${expo.imgName}" width="300">
                <div class="card-img-overlay ">
                    <c:choose>
                        <c:when test="${sessionScope.lang == 'ua'}">
                            <span class="d-inline p-2 bg-dark text-white font-weight-bold">${expo.titleUa}</span>
                        </c:when>
                        <c:when test="${sessionScope.lang == 'en'}">
                            <span class="d-inline p-2 bg-dark text-white font-weight-bold">${expo.title}</span>
                        </c:when>
                        <c:otherwise>
                            <span class="d-inline p-2 bg-dark text-white font-weight-bold">${expo.title}</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="card-body">
                    <h5 class="card-title">${expo.startDate} - ${expo.endDate}</h5>
                    <c:choose>
                        <c:when test="${sessionScope.lang == 'ua'}">
                            <p class="card-text">${expo.descriptionUa}</p>
                        </c:when>
                        <c:when test="${sessionScope.lang == 'en'}">
                            <p class="card-text">${expo.description}</p>
                        </c:when>
                        <c:otherwise>
                            <p class="card-text">${expo.description}</p>
                        </c:otherwise>
                    </c:choose>
                    <h5 class="card-title">
                        <c:choose>
                        <c:when test="${sessionScope.lang == 'ua'}">
                            ${expo.ticketPrice * 30}</h5>
                    </c:when>
                    <c:when test="${sessionScope.lang == 'en'}">
                        ${expo.ticketPrice}</h5>
                    </c:when>
                    <c:otherwise>
                        ${expo.ticketPrice}</h5>
                    </c:otherwise>
                    </c:choose>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <c:if test="${sessionScope.user.roles.contains(sessionScope.roles[0])}">
                                <a class="btn btn-sm btn-outline-secondary"
                                   href="${pageContext.request.contextPath}/app/ticket/${expo.id}/buy"
                                   type="button">Buy</a>
                            </c:if>
                            <c:if test="${sessionScope.user.roles.contains(sessionScope.roles[1])}">
                                <a class="btn btn-sm btn-outline-secondary"
                                   href="${pageContext.request.contextPath}/app/expo/edit/${expo.id}"
                                >Edit</a>
                            </c:if>
                        </div>
                        <small class="text-muted">${expo.amount} left</small>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>


</body>
</html>
