<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Add new Expo</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container-fluid bg-light">
<jsp:include page="blocks/header.jsp"/>
<div class="container mt-5">
    <h1>Add new Expo</h1>
    <form method="post" action="${pageContext.request.contextPath}/app/expo/add">
        <div><label>Capture: </label><input name="imgName" type="text" value="${expoDTO.imgName}"/></div>
        <div><label>Title eng: </label><input name="title" type="text" value="${expoDTO.title}"/></div>
        <div><label>Title ua: </label><input name="title_ua" type="text" value="${expoDTO.titleUa}"/></div>
        <div><label>Desc eng: </label></div>
        <textarea name="description" type="text" value="${expoDTO.description}"></textarea>
        <div><label>Desc ua: </label></div>
        <textarea name="description_ua" type="text" value="${expoDTO.descriptionUa}"></textarea>
        <div><label>Start date: </label><input name="startDate" type="date" value="${expoDTO.startDate}"/></div>
        <div><label>End date: </label><input name="endDate" type="date" value="${expoDTO.endDate}"/></div>
        <div><label>Amount: </label><input name="amount" type="number" value="${expoDTO.amount}"/></div>
        <div><label>Price: </label><input name="ticket_price" type="number" value="${expoDTO.ticketPrice}"/></div>
        <c:forEach items="${holles}" var="hole">
            <input name="holles" value="${hole}" type="checkbox">${hole}
        </c:forEach>
        <p><input class="btn btn-success" value="<fmt:message key="add"/>" type="submit"/></p>
        <c:if test="${sessionScope.valid != null}"> ${sessionScope.valid}</c:if>
    </form>
</div>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>