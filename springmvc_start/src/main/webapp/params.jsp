<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/2/5
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求参数绑定入门</title>
</head>
<body>
<%--<a href="param/testParamDemo?username=阿狗&password=Cqc112233.">绑定字符串参数</a>--%>

<%--<form action="param/saveAccount" method="post">
    账户姓名：<input type="text" name="username"></br>
    账户密码：<input type="password" name="password"></br>
    账户金额：<input type="text" name="money"></br>
    用户姓名：<input type="text" name="user.uname"></br>
    用户年龄：<input type="text" name="user.age"></br>
    <input type="submit" value="提交"></br>
</form>--%>

<%--封装集合类型的数据
<form action="param/saveAccount" method="post">
    账户姓名：<input type="text" name="username"></br>
    账户密码：<input type="password" name="password"></br>
    账户金额：<input type="text" name="money"></br>

    用户姓名：<input type="text" name="ll[0].uname"></br>
    用户年龄：<input type="text" name="ll[0].age"></br>

    用户姓名：<input type="text" name="mm['one'].uname"></br>
    用户年龄：<input type="text" name="mm['one'].age"></br>
    <input type="submit" value="提交"></br>
</form>--%>

<form action="param/saveUser" method="post">

    用户姓名：<input type="text" name="uname"></br>
    用户年龄：<input type="text" name="age"></br>
    用户生日：<input type="text" name="birthday"></br>
    <input type="submit" value="提交"></br>
</form>
</body>
</html>
