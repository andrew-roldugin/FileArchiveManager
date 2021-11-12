<%@ page import="ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> Hello world</h1>
</body>
<c:set var="checkLogin" scope="session" value="${ApplicationContext.getInstance().isLoggedIn()}" />
<c:set var="id" scope="session" value="${ApplicationContext.getInstance().getUser().getId()}" />
    <c:choose>
        <c:when test="${!checkLogin}">
            <c:redirect url="/auth-page.jsp" />
        </c:when>
        <c:otherwise>
            <c:redirect url="/user/${id}" />
        </c:otherwise>
    </c:choose>
</html>
