<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Expo editor</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container-fluid bg-light">
<jsp:include page="blocks/header.jsp"/>
<div class="container mt-5">
    <h1>Edit Expo</h1>
    <form method="post" action="${pageContext.request.contextPath}/app/expo/edit/${id}">
        <div><label>Capture: </label><input name="imgName" value="${expo.imgName}" type="text"/></div>
        <div><label>Title eng: </label><input name="title" value="${expo.title}" type="text"/></div>
        <div><label>Title ua: </label><input name="title_ua" value="${expo.titleUa}" type="text"/></div>
        <div><label>Desc eng: </label></div>
        <textarea name="description" type="text" value="${expo.description}"></textarea>
        <div><label>Desc ua: </label></div>
        <textarea name="description_ua" type="text" value="${expo.descriptionUa}"></textarea>
        <div><label>Start date: </label><input name="startDate" value="${expo.startDate}" type="date"/></div>
        <div><label>End date: </label><input name="endDate" value="${expo.endDate}" type="date"/></div>
        <div><label>Amount: </label><input name="amount" value="${expo.amount}" type="number"/></div>
        <div><label>Price: </label><input name="ticket_price" value="${expo.ticketPrice}" type="number"/></div>
        <input name="id" value="${id}" type="hidden">
        <c:forEach items="${holles}" var="hole">
            <input
                    <c:if test="${expo.holles.contains(hole)}">checked="checked"</c:if> field="${expo.holles}"
                    name="holles"
                    value="${hole}" type="checkbox">${hole}
        </c:forEach>
        <p><input class="btn btn-success" value="<fmt:message key="edit"/>" type="submit"/></p>
        <c:if test="${sessionScope.valid != null}"> ${sessionScope.valid}</c:if>
    </form>
</div>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
