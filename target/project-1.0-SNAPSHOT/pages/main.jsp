<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Admin menu</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/show_all_matches" method="get">
    <input type="submit" value="Вывод всех матчей">
</form>
<form action="${pageContext.request.contextPath}/sort_match_by_name" method="get">
    <input type="submit" value="Сортировка матчей по названию">
</form>
<form action="${pageContext.request.contextPath}/sort_match_by_date" method="get">
    <input type="submit" value="Сортировка матчей по дате">
</form>
<form action="${pageContext.request.contextPath}/search_match_by_id" method="post">
    <input type="text" name="matchId" value="${match.getSportId()}">
    <input type="submit" value="Поиск матча по ID">
</form>
<div class="alert alert-danger" role="alert">
    <h2>${errorDeleteMatch}</h2>
</div>
<c:if test="${matches != null}">

    <table border="1" style="align-content: center">
        <%--<colgroup>
            <col style="background-color: darkgray">
        </colgroup>--%>
        <tr>
            <th>ID спорта</th>
            <th>Название спорта</th>
            <th>Игрок 1</th>
            <th>Игрок 2</th>
            <th>Коэффциент 1 игрока</th>
            <th>Коэффциент на ничью</th>
            <th>Коэффциент 2 игрока</th>
            <th>Дата игры</th>
            <c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
                <th>Удаление матчей</th>
            </c:if>
        </tr>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.getSportId()}</td>
                <td>${match.getName()}</td>
                <td>${match.getPlayer1()}</td>
                <td>${match.getPlayer2()}</td>
                <td>${match.getRate1()}</td>
                <td>${match.getRate0()}</td>
                <td>${match.getRate2()}</td>
                <td>${match.getDate()}</td>
                <c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
                    <td>
                        <form action="${pageContext.request.contextPath}/delete_match" method="post">
                            <input type="hidden" name="matchId" value="${match.getSportId()}">
                            <input type="submit" value="Удалить матч">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
<%--    <form>
        <input type="text" id="user" name="sport_id" placeholder="ID" />
        <button type="submit">&#xf0da;</button>
    </form>--%>
</c:if>
</body>
</html>
