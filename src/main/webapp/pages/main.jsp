
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/show_all_users" method="get">
    <input type="submit" value="Show all users">
</form>
<c:forEach items="${users}" var="user">
    <h1>${user.getId()}</h1>
    <h1>${user.getLogin()}</h1>
    <h1>${user.getPassword()}</h1>
    <h1>${user.getRoleType()}</h1>
<%--    todo добавить поля с инфой--%>
</c:forEach>
</body>
</html>
