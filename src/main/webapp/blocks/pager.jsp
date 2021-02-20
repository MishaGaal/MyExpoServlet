<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 12.02.2021
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<c:if test="${sessionScope.pages.size()>0}">
    <div class="container">
        <ul class="pagination py-5 ml-4">
            <li class="page-item ">
                <a class="page-link  border-secondary text-muted" tabindex="-1"
                   href="?page=0"><fmt:message key="start"/></a>
            </li>
            <c:forEach begin="1" end="${sessionScope.pages.count-1}" var="i">
                <li class="page-item">
                    <a class="page-link border-secondary text-muted" tabindex="-1" href="?page=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item ">
                <a class="page-link  border-secondary text-muted" tabindex="-1"
                   href="?page=${sessionScope.pages.count-1}"><fmt:message key="end"/></a>
            </li>
        </ul>
    </div>
</c:if>
