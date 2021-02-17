<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>MYEXPO</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container-fluid bg-light">
<jsp:include page="blocks/header.jsp"/>
<div class="container">
    <jsp:include page="blocks/welcome.jsp"/>
</div>
<jsp:include page="blocks/expos.jsp"/>

<jsp:include page="blocks/pager.jsp"/>

<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
