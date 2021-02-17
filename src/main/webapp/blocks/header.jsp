<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 06.02.2021
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/app"><fmt:message key="expo"/></a>
        <button aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation"
                class="navbar-toggler" data-bs-target="#navbarCollapse" data-bs-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a aria-disabled="true" class="nav-link disabled" href="#" tabindex="-1">${user}</a>
                </li>
                <li class="nav-item active">
                    <a aria-current="page" class="nav-link" href="${pageContext.request.contextPath}/app/main">
                        <fmt:message key="main"/></a>
                </li>
                <c:if test="${sessionScope.user.roles.contains(sessionScope.roles[0])}">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/ticket"
                        ><fmt:message key="expo.my"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.roles.contains(sessionScope.roles[1])}">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/expo"
                        ><fmt:message key="expo.all"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.roles.contains(sessionScope.roles[1])}">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/user"
                    ><fmt:message key="expo.user"/></a>
                </li>
            </ul>
            </c:if>
        </div>
    </div>
    <a class="float-right" href="?sessionLocale=ua"><img style="width:30px;height:28px"
                                                         src="${pageContext.request.contextPath}/images/ukraine.svg"></a>
    <a class="float-right mr-3" href="?sessionLocale=en"><img style="width:30px;height:28px"
                                                              src="${pageContext.request.contextPath}/images/united-kingdom.svg"></a>

    <c:if test="${sessionScope.user.roles.contains(sessionScope.roles[0])}">
        <form method="post" action="${pageContext.request.contextPath}/app/logout">
            <input class="btn btn-danger btn-sm" value="<fmt:message key="sign.out"/>" type="submit"/>
        </form>
    </c:if>
</nav>
