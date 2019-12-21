
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>小游戏集合网站欢迎您</title>
</head>
<style>
    body{
        background-image: url("img/picture(3).jpg");
        background-repeat: no-repeat;
        background-size: 100%;
    }
</style>
<body>
<h1 align="center"><%=(String)request.getSession().getAttribute("user")%>，开启你的路程吧！</h1>
</body>
</html>
