
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Sign up form</title>
    <link rel="stylesheet" href="css/styleSignInAndSignUp.css">
</head>
<body>
<h1>Регистрация</h1>
<div id="wrapper">
<%--    todo pattern required--%>
    <form id="signin" action="${pageContext.request.contextPath}/sign_up" method="post" autocomplete="off">
        <input type="text" id="login" name="login" placeholder="Логин" />
        <input type="password" id="pass" name="password" placeholder="Пароль" />
        <input type="password" id="repeat_password" name="repeat_password" placeholder="Повторите пароль" />
        <input type="text" id="name" name="name" placeholder="Имя" />
        <input type="text" id="surname" name="surname" placeholder="Фамилия" />
        <input type="text" id="age" name="age" placeholder="Возраст" />
        <input type="text" id="phone" name="phone" placeholder="Мобильный телефон" />
        <input type="text" id="mail" name="mail" placeholder="Электронная почта" />
        <button type="submit">&#xf0da;</button>
        <p>Авторизация<a href="${pageContext.request.contextPath}/sign_in"> нажмите сюда</a></p>
        <div class="alert alert-danger" role="alert">
            <h2>${errorLogin}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorPassword}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorInitials}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorAge}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorPhone}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorMail}</h2>
        </div>
        <div class="alert alert-danger" role="alert">
            <h2>${errorExist}</h2>
        </div>
    </form>
</div>
</body>
</html>

