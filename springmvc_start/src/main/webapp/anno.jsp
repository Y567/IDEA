<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/2/5
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用注解</title>
</head>
<body>
<%--<a href="anno/testRequestParam?name=阿狗">RequestParam测试</a>--%>

<%--<form action="anno/testRequestBody" method="post">

    用户姓名：<input type="text" name="uname"></br>
    用户年龄：<input type="text" name="age"></br>
    <input type="submit" value="提交"></br>
</form>--%>

<a href="anno/testPathVariable/10982937">PathVariable测试</a>
</br>
<a href="anno/testRequestHeader">RequestHeader测试</a>
</br>
<a href="anno/testCookieValue">CookieValue测试</a>
</br>
<form action="anno/testModelAttribute" method="post">
    用户姓名：<input type="text" name="uname"></br>
    用户年龄：<input type="text" name="age"></br>
    <input type="submit" value="提交"></br>
</form>
</br>

<a href="anno/testSessionAttribute">testSessionAttribute测试</a>
<a href="anno/getDataFromRequest">get数据测试</a>
<a href="anno/delSessionData">delSession测试</a>

</body>
</html>
