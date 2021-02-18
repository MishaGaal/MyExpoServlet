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
<body class="container-fluid bg-light mt-5">
<jsp:include page="blocks/header.jsp"/>
<div class="container">
    <h1>Edit Expo</h1>
    <form method="post" action="${pageContext.request.contextPath}/app/expo/edit/${expo.id}">
        <div><label>Capture: </label><input name="imgName" type="text" value="${expo.imgName}"/></div>
        <div><label>Title eng: </label><input name="title" type="text" value="${expo.title}"/></div>
        <div><label>Title ua: </label><input name="title_ua" type="text" value="${expo.titleUa}"/></div>
        <div><label>Desc eng: </label></div>
        <textarea name="description" type="text">${expo.description}</textarea>
        <div><label>Desc ua: </label></div>
        <textarea name="description_ua" type="text">${expo.descriptionUa}</textarea>
        <div><label>Start date: </label><input name="startDate" type="date" value="${expo.startDate}"/></div>
        <div><label>End date: </label><input name="endDate" type="date" value="${expo.endDate}"/></div>
        <div><label>Amount: </label><input name="amount" type="number" value="${expo.amount}"/></div>
        <div><label>Price: </label><input name="ticket_price" type="number" value="${expo.ticketPrice}"/></div>
        <c:forEach items="${holles}" var="hole">
            <input
                    <c:if test="${expo.holles.contains(hole)}">checked="checked"</c:if> name="holles"
                    value="${hole}" type="checkbox">${hole}
        </c:forEach>
        <p><input class="btn btn-success" value="<fmt:message key="edit"/>" type="submit"/></p>
        <c:if test="${sessionScope.valid != null}"> ${sessionScope.valid}</c:if>
        <input name="id" type="hidden" value="${expo.id}"/>
    </form>
</div>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
