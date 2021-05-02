
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
</head>
<body>
<h1>Добавить игру</h1>
<div id="wrapper">
<%--    todo pattern required--%>
<form id="signin" action="${pageContext.request.contextPath}/add_match" method="post">
    <input type="text" id="user" name="name" placeholder="Название" />
    <input type="text" id="player1" name="player1" placeholder="Игрок 1" />
    <input type="text" id="player2" name="player2" placeholder="Игрок 2" />
    <input type="text" id="rate1" name="rate1" placeholder="Коэффициент на 1-го игрока" />
    <input type="text" id="rate0" name="rate0" placeholder="Коэффициент на ничью" />
    <input type="text" id="rate2" name="rate2" placeholder="Коэффициент на 2-го игрока" />
    <input type="text" id="date" name="date" placeholder="Дата проведения игры" />
    <button type="submit">&#xf0da;</button>
</form>
</body>
</html>
