<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2019/12/30
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Ajax</title>
  </head>
  <script src="js/jquery-3.3.1.js"></script>
  <script>
    function fun() {
      /*$.ajax({
        url:"ajaxServlet",
        type:"POST",
        //data是访问字符串的值
        data:'{username:"高洋洋",age:23}',  //这里加上单引号因为不加的话在springMvc中封装数据时会报错
        success:function (data) {
          alert(data);//该方法是服务器成功返回数据时调用
        },
        error:function () {
          //该方法是发生错误调用的方法
          alert("出错了");
        }
      });*/


 /*     //2.get请求
        $.get("ajaxServlet",{username:"gyy",age:23},function (data) {
            alert(data);
        },"text");*/


        //3.0post请求
        //2.get请求
        $.post("ajaxServlet",{username:"gyy",age:23},function (data) {
            alert(data);
        },"text");
    };
  </script>
  <body>
  <input type="button" value="发送异步请求" onclick="fun();">
  <input>
  </body>
</html>
