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
    <title>响应</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#bt").click(function () {
                // alert("sada");

                //发送异步请求
                /*$.post("user/testAjax",{"username":"阿狗","password":"ag112233","age":23},function(data){
                    alert("哈喽");
                },"post");*/


                $.ajax({
                    url: "user/testAjax",
                    contentType: "application/json;charset=utf-8",
                    type: "post",
                    data: '{"username":"hehe","password":"ag112233","age":23}',
                    dataType: "json",
                    success: function (data) {
                        //将后台返回来的数据显示出来
                        alert(data.username);
                    }
                });
            });
        });
    </script>

</head>
<body>

<a href="user/testString">测试用户响应</a>
<br>
<a href="user/testVoid">void返回值响应</a>
<br>
<a href="user/testModelAndView">testModelAndView返回值响应</a>
<br>
<a href="user/testKey">返回值Key响应</a>
<br>

<button id="bt">发送Ajax请求</button>


</body>
</html>
