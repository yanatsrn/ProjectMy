<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
<body>
<form action="${pageContext.request.contextPath}/show_all_matches" method="get">
    <input  type="submit" value="Вывод всех матчей">
</form>
<form action="${pageContext.request.contextPath}/sort_match_by_name" method="get">
    <input type="submit" value="Сортировка матчей по названию">
</form>
<form action="${pageContext.request.contextPath}/sort_match_by_date" method="get">
    <input type="submit" value="Сортировка матчей по дате">
</form>
<c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
    <td>
        <form action="${pageContext.request.contextPath}/add_match" method="get">
            <input type="submit" value="Добавить матч">
        </form>
    </td>
</c:if>
<form action="${pageContext.request.contextPath}/search_match_by_id" method="get" autocomplete="off">
    <input type="text" name="matchId">
    <input type="submit" value="Поиск матча по ID">
</form>
<form action="${pageContext.request.contextPath}/search_match_by_name" method="get" autocomplete="off">
    <input type="text" name="matchName">
    <input type="submit" value="Поиск матча по виду спорта  ">
</form>
<div class="alert alert-danger" role="alert">
    <h2>${errorDeleteMatch}</h2>
</div>
<c:if test="${user.getRoleType().toString() eq 'USER'}">
    <form action="${pageContext.request.contextPath}/diagram" method="get">
        <input type="submit" value="Сделать круговую диаграмму">
    </form>
</c:if>
<c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/send_report" method="get">
        <input type="submit" value="Сделать отчет по пользователям">
    </form>
</c:if>
<c:if test="${user.getRoleType().toString() eq 'USER'}">
    <form action="${pageContext.request.contextPath}/graphics" method="get">
        <input type="submit" value="Сделать график">
    </form>
</c:if>
<c:if test="${user.getRoleType().toString() eq 'USER'}">
    <form action="${pageContext.request.contextPath}/send_report_matches" method="get">
        <input type="submit" value="Сделать отчет по матчам">
    </form>
</c:if>

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
            <c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
                <th>Изменить матч</th>
            </c:if>
            <c:if test="${user.getRoleType().toString() eq 'USER'}">
                <th>Сделать ставку</th>
            </c:if>
            <c:if test="${user.getRoleType().toString() eq 'USER'}">
                <th>Использовать метод</th>
            </c:if>
            <c:if test="${user.getRoleType().toString() eq 'USER'}">
                <th>Использовать метод</th>
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
                <c:if test="${user.getRoleType().toString() eq 'ADMIN'}">
                    <td>
                        <form action="${pageContext.request.contextPath}/update_match" method="get">
                            <input type="hidden" name="matchId" value="${match.getSportId()}">
                            <input type="submit" value="Изменить матч">
                        </form>
                    </td>
                </c:if>
                <c:if test="${user.getRoleType().toString() eq 'USER'}">
                    <td>
                        <form action="${pageContext.request.contextPath}/make_a_bet" method="get">
                            <input type="hidden" name="matchId" value="${match.getSportId()}">
                            <input type="submit" value="Сделать ставку">
                        </form>
                    </td>
                </c:if>
                <c:if test="${user.getRoleType().toString() eq 'USER'}">
                    <td>
                        <form action="${pageContext.request.contextPath}/method" method="get">

                            <input type="hidden" name="match_id" value="${match.getSportId()}">
                            <input type="hidden" name="player" value="${match.getPlayer1()}">
                            <input type="submit" value="Рассчитать 1-го игрока">
                        </form>
                    </td>
                </c:if>
                <c:if test="${user.getRoleType().toString() eq 'USER'}">
                    <td>
                        <form action="${pageContext.request.contextPath}/method" method="get">

                            <input type="hidden" name="match_id" value="${match.getSportId()}">
                            <input type="hidden" name="player" value="${match.getPlayer2()}">
                            <input type="submit" value="Рассчитать 2-го игрока">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${match != null}">
    <table border="1" style="align-content: center">
        <tr>
            <th>ID спорта</th>
            <th>Название спорта</th>
            <th>Игрок 1</th>
            <th>Игрок 2</th>
            <th>Коэффциент 1 игрока</th>
            <th>Коэффциент на ничью</th>
            <th>Коэффциент 2 игрока</th>
            <th>Дата игры</th>
        </tr>
        <tr>
            <td>${match.getSportId()}</td>
            <td>${match.getName()}</td>
            <td>${match.getPlayer1()}</td>
            <td>${match.getPlayer2()}</td>
            <td>${match.getRate1()}</td>
            <td>${match.getRate0()}</td>
            <td>${match.getRate2()}</td>
            <td>${match.getDate()}</td>
        </tr>
    </table>
</c:if>

<div class="alert alert-danger" role="alert">
    <h2>${errorRole}</h2>
</div>
<div class="alert alert-danger" role="alert">
    <h2>${errorMatchNotFound}</h2>
</div>
</body>
</head>
</html>
