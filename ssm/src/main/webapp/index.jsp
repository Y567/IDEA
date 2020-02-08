<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/2/8
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<a href="account/testFindAccountAll">测试查询</a>

<h3>测试保存（配置了声名式事务控制）</h3>
<form action="account/testSaveAccount" method="post">
    姓名：<input type="text" name="name"><br/>
    金钱：<input type="text" name="money"><br/>
    <input type="submit" value="保存"/>
</form>

</body>
</html>
