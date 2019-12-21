<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改玩家信息</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <input type="hidden" value="${user.id}" name="id">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" class="form-control" id="username" name="username" readonly="readonly" value="${user.username}" placeholder="请输入一个邮箱作为账号">
            </div>

            <div class="form-group">
                <label for="password">密码：</label>
                <input type="text" class="form-control" id="password" name="password" value="${user.password}" placeholder="请设置一个密码，至少包含6位数字">
            </div>

            <div class="form-group">
                <label for="tell">手机号：</label>
                <input type="text" class="form-control" id="tell" name="tell" value="${user.tell}" placeholder="请输入您的手机号"/>
            </div>

            <div class="form-group">
                <label>性别：</label>
                <c:if test="${user.gender=='男'}">
                    <input type="radio" name="gender" value="男" checked="checked"/>男
                    <input type="radio" name="gender" value="女"/>女
                </c:if>

                <c:if test="${user.gender=='女'}">
                    <input type="radio" name="gender" value="男"/>男
                    <input type="radio" name="gender" value="女" checked="checked"/>女
                </c:if>
            </div>


            <div class="form-group">
                <label for="birthday">出生日期：</label>
                <input type="date" class="form-control" id="birthday" name="birthday" value="${user.birthday}">
            </div>

            <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" onclick="javascript:history.back(-1);" value="返回" />
            </div>
        </form>
        </div>
    </body>
</html>