<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/2/5
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>文件上传</title>

</head>
<body>
<%--请求方式必须时post因为文件很大不能让在浏览器栏里显示吧，enctype设置为该值可以将文件保存在请求消息体中以MIME协议
保存文件更有利于解析文件--%>

<h3>传统的文件上传</h3><br/>
<form action="user/fileupload" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload">
    <br/>
    <input type="submit" value="上传">
</form>

<h3>springmvc的文件上传</h3><br/>
<form action="user/fileupload2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload">
    <br/>
    <input type="submit" value="上传">
</form>

<h3>springmvc的跨服务器文件上传</h3><br/>
<form action="user/fileupload3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload">
    <br/>
    <input type="submit" value="上传">
</form>

</body>
</html>
