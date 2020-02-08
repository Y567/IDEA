<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/2/8
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>

<h3>测试springmvc的响应页面</h3>
<c:forEach items="${list}" var="account">
    ${account.name}<br/>
</c:forEach>

</body>
</html>
