<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    todo pattern required--%>
<c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/update_match" method="post">
        <input type="hidden" name="id" value="${match.getSportId()}">
        <input type="text" name="name" value="${match.getName()}">
        <input type="text" name="player1" value="${match.getPlayer1()}">
        <input type="text" name="player2" value="${match.getPlayer2()}">
        <input type="text" name="rate1" value="${match.getRate1()}">
        <input type="text" name="rate0" value="${match.getRate0()}">
        <input type="text" name="rate2" value="${match.getRate2()}">
        <input type="text" name="date" value="${match.getDate()}">
        <input type="submit" value="Изменить матч">
    </form>
</c:if>
<div class="alert alert-danger" role="alert">
    <h2>${errorRole}</h2>
</div>
</body>
</html>
