
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>小游戏集合网</title>
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
    .layout{
      width: 500px;
      height:300px;
      opacity: 0.6;
      border: 1px solid palegreen;
      margin: auto;
      margin-top: 125px;
    }
    a{
      float: right;
      color: hotpink;
    }
    #username,#password,#checkCode{
      border: 1px solid cornflowerblue;
      width: 251px;
      height: 32px;
      border-radius: 10px;
      padding-left: 10px;
      margin-left: 50px;
    }
    #img{
      border: 1px solid cornflowerblue;
      border-radius: 10px;
      padding-left: 1px;
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
    .error{
      text-align: center;
      font-style: oblique;
      color: palegreen;
    }

  </style>
  <script>
    window.onload = function(){
        document.getElementById("img").onclick = function(){
        this.src = "${pageContext.request.contextPath}/checkCodeDemo?time="+new Date().getMilliseconds();
      }
    }
  </script>
</head>
<body>
<div class = "layout">
  <form action="${pageContext.request.contextPath}/loginServlet" method="post">
    <table>
      <caption><p>欢迎小友登录</p></caption>
      <tr>
        <td class="td_left"><label for="username">用户名：</label></td>
        <td class="td_right"><input id="username" type="text" name="username" placeholder="请输入您的昵称（邮箱地址）"></td>
      </tr>
      <tr>
        <td class="td_left"><label for = "password">密码：</label></td>
        <td class="td_right"><input id="password" type="password" name="password" placeholder="请输入您的密码"></td>
      </tr>
      <tr>
        <td class="td_left"><label>验证码：</label></td>
        <td class="td_right"><input id="checkCode" type = "text" name = "checkCode"></td>
      </tr>
      <tr>
        <td class="td_left"><label>        </label></td>
        <td class="td_right"><img id = "img" src = "${pageContext.request.contextPath}/checkCodeDemo"></td>
      </tr>
      <tr>
        <td class="td_left"><input type="checkbox" name="remember"><label>记住密码</label></td>
        <td class="td_right"><a href="">忘记密码?</a></td>
      </tr>
      <tr>
        <td class="td_left"><input id="login" type="submit" value="登录" onclick=""></td>
        <td  class="td_right"><input id="enroll" type="button" value="注册" onclick=""></td>
      </tr>
    </table>
  </form>
</div>
<br/>
<div class="error"><%=request.getSession().getAttribute("login_error")==null?"":request.getSession().getAttribute("login_error")%><%request.getSession().removeAttribute("login_error");%></div>
<div class="error"><%=request.getSession().getAttribute("cc_error")==null?"":request.getSession().getAttribute("cc_error")%><%request.getSession().removeAttribute("cc_error");%></div>
<hr color="palegreen"/>
<div class = "time"></div>
<script>
  //加时间
  var t = document.getElementsByClassName("time")[0];
  function time(){
    var time = new Date();
    var hour = time.getHours();
    var minute = time.getMinutes();
    var seconds = time.getSeconds();
    t.innerHTML = "当前时间：" + hour +":" + minute + ":" + seconds;
  }
  setInterval(time,1000);
  var enroll = document.getElementById("enroll");
  enroll.onclick = function () {
    location.href = "eroll.jsp";
  }
</script>

</body>
</html>
