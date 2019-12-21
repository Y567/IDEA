<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/27
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>你专属的游戏账号注册</title>
    <style>
        body{
            background-image: url("img/picture(3).jpg");
            background-repeat: no-repeat;
            background-size: 100%;
        }
        p{
            color: lawngreen;
            font-size:25px;
        }
        label{
            color: lawngreen;
        }
        table{
            width: 500px;
        }
        .td_left{
            text-align: right;
            width: 100px;
        }
        .td_right{
            border-radius: 10px;
            padding-left: 10px;
        }
        .sex{
            margin-left: 50px;
        }
        .date{
            margin-left: 50px;
        }
        .layout{
            width: 500px;
            height:320px;
            opacity: 0.6;
            border: 1px solid palegreen;
            margin: auto;
            margin-top: 125px;
        }
        a{
            float: right;
            color: hotpink;
        }
        .boder{
            border: 1px solid cornflowerblue;
            width: 251px;
            height: 32px;
            border-radius: 10px;
            padding-left: 10px;
            margin-left: 50px;
        }
        #login{
            float: left;
            padding: 10px;
            padding-left: 25px;
            padding-right: 25px;
            border-radius: 10px;
        }
        #enroll{
            padding: 10px;
            padding-left: 25px;
            padding-right: 25px;
            float: right;
            border-radius: 10px;
        }
        .time{
            font-style: oblique;
            color: powderblue;
        }
    </style>
</head>
<body>
<div class = "layout">
    <form action="${pageContext.request.contextPath}/erollServlet" method="post" onsubmit="return check();">
        <table>
            <caption><p>注册你的专属账号</p></caption>
            <tr>
                <td class="td_left"><label for="username">用户名：</label></td>
                <td class="td_right"><input class="boder" id="username" type="text" name="username" placeholder="请输入一个有效的邮箱地址"></td>
            </tr>
            <tr>
                <td class="td_left"><label for = "password">密码：</label></td>
                <td class="td_right"><input class="boder" id="password" type="password" name="password" placeholder="请设置你的密码（至少6位数字）"></td>
            </tr>
            <tr>
                <td class="td_left"><label for = "tell">手机号：</label></td>
                <td class="td_right"><input class="boder" id="tell" type="text" name="tell" placeholder="请输入你的手机号"></td>
            </tr>
            <tr>
                <td class="td_left"><label for = "gender">性别：</label></td>
                <td class="td_right"><input class="sex" id="gender" type="radio" name="gender" value="男" checked>男<input class="sex" type="radio" name="gender" value="女">女</td>
            </tr>
            <tr>
                <td class="td_left"><label for = "birthday">出生日期：</label></td>
                <td class="td_right"><input class = "date" id="birthday" type="date" name="birthday" value="2019-01-01"></td>
            </tr>
            <tr>
                <td  class="td_left"><input id="login" type="button" value="已有账号" onclick="login();"></td>
                <td  class="td_right"><input id="enroll" type="submit" value="立即注册"></td>
            </tr>
        </table>
    </form>
</div>
<br/>
<br/>
<br/>
<br/>
<hr color="palegreen">
<div class = "time"></div>
<script>
    //加时间
    var t = document.getElementsByClassName("time")[0];
    function time(){
        var time = new Date();
        var hour = time.getHours();
        var minute = time.getMinutes();
        var seconds = time.getSeconds();
        t.innerHTML = "当前时间："+ hour +":" + minute + ":" + seconds;
    }
    setInterval(time,1000);
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
    var login = document.getElementById("login");
    login.onclick = function(){
        location.href = "index.jsp";
    }
</script>
</body>
</html>