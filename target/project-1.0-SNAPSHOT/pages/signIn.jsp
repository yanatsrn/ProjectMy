
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
    <form id="signin" action="${pageContext.request.contextPath}/sign_in" method="post" autocomplete="off">
        <input type="text" id="user" name="login" placeholder="Логин" required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"/>
        <input type="password" id="pass" name="password" placeholder="Пароль" required pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"/>
        <button type="submit">&#xf0da;</button>
        <p>Регистрация<a href="${pageContext.request.contextPath}/sign_up"> нажмите сюда</a></p>
        <div class="alert alert-danger" role="alert">
            <h2>${errorSignInLoginAndPassword}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorSignInUserNotFound}</h2>
        </div>
    </form>
</div>

</body>
</html>

