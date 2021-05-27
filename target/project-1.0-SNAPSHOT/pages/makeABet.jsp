
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Make a bet</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
</head>
<body>
<c:if test="${user.getRoleType().toString() eq 'USER'}">
    <form action="${pageContext.request.contextPath}/make_a_bet" method="post">
        <input type="hidden" name="id" value="${user.getId()}">
        <input type="hidden" name="matchId" value="${matchId}">
        <input type="text" name="sum" value="${user.getSum()}">
        <input type="submit" value="Сделать ставку">
    </form>
</c:if>
</body>
</html>
