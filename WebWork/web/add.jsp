<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加玩家</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        function check() {
            //表单校验
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var tell = document.getElementById("tell").value;
            var reg0 = new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);//邮箱
            var reg1 = new RegExp(/\d{6}/);//密码
            var reg2 = new RegExp(/^1(3|4|5|6|7|8|9)\d{9}$/);//电话号码
            var f1 = reg0.test(username);
            var f2 = reg1.test(password);
            var f3 = reg2.test(tell);
            if(f1 && f2 && f3){
                //表单校验通过，可以提交
                return true;
            } else{
                if(!f1){
                    // alert("恭喜你注册成功由于系统还未完善，先来体验一把吧！！！");
                    alert("你输入的邮箱地址有误！");
                }
                if(!f2){
                    // alert("恭喜你注册成功由于系统还未完善，先来体验一把吧！！！");
                    alert("你设置的密码有误！");
                }
                if(!f3){
                    // alert("恭喜你注册成功由于系统还未完善，先来体验一把吧！！！");
                    alert("请输入正确的手机号！");
                }
                return false;
            }
        }



        function backStep(){

        }
    </script>
</head>
<body>
<div class="container">
    <center><h3>添加玩家页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="post" onsubmit="return check();">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="请输入一个邮箱作为账号">
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="请设置一个密码，至少包含6位数字">
        </div>

        <div class="form-group">
            <label for="tell">手机号：</label>
            <input type="text" class="form-control" id="tell" name="tell" placeholder="请输入您的手机号"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>


        <div class="form-group">
            <label for="birthday">出生日期：</label>
            <input type="date" class="form-control" id="birthday" name="birthday">
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