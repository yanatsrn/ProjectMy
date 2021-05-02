
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Sign in form</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
</head>
<body>
<h1>Авторизация</h1>
<div id="wrapper">
    <%--    todo pattern required--%>
    <form id="signin" action="${pageContext.request.contextPath}/sign_in" method="post" autocomplete="off">
        <input type="text" id="user" name="login" placeholder="Логин" />
        <input type="password" id="pass" name="password" placeholder="Пароль" />
        <button type="submit">&#xf0da;</button>
        <p>Регистрация<a href="${pageContext.request.contextPath}/sign_up"> нажмите сюда</a></p>
    </form>
</div>

</body>
</html>

