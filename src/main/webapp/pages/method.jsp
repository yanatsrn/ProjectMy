
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Method</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
</head>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
<h1>${percent}%</h1>
</body>
</html>
