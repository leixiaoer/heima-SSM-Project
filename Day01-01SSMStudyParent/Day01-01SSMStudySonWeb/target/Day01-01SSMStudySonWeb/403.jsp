<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限不足</title>
</head>
<body>
    <h3>该用户权限不足！！！</h3>
    <% response.setHeader("refresh", "5;URL=/login.jsp");%>
</body>
</html>
