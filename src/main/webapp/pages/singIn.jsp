
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello Sigh in</h1>
<form action="${pageContext.request.contextPath}/sign_in" method="post">
    <input type="text" name="login">
    <input type="password" name="password">
    <input type="submit" value="Submit">
</form>
</body>
</html>
