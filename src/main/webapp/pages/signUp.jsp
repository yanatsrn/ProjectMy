<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.03.2021
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Sign Up</h1>
<form action="${pageContext.request.contextPath}/sign_in" method="post">
    <input type="text" name="login">
    <input type="password" name="password">
    <input type="text" name="surname">
    <input type="text" name="name">
    <input type="text" name="age">
    <input type="text" name="phone">
    <input type="text" name="mail">
    <input type="submit" value="Submit">
</form>
</body>
</html>
