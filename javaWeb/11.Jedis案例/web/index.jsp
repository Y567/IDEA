<%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2019/12/31
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>下拉列表显示省份案例异步请求实现</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        //当页面加载完毕后即可发送异步请求
        $.get("provinceServlet",{},function (data) {
          //需要遍历json对象，然后利用append方法在option标签后面加入元素
          var province = $("#province");
          $(data).each(function () {
            //获取select标签
            //创建option
            var option = "<option name = '"+this.id+"'>"+this.name+"</option>";
            province.append(option);
          });
        },"json");
      });
    </script>
  </head>
  <body>
  <label for="province"></label><select id="province">
    <option>--请选择省份--</option>
  </select>
  </body>
</html>
