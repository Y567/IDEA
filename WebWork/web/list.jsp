<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/4
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>玩家信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id){
            //防止用户误删，进行确认
            if(confirm("您确定要删除吗？")){
                //确定要删除
                location.href = "${pageContext.request.contextPath}/deleteUserServlet?id="+id;
            }
        }

        function submitIds() {
            if(confirm("您确定要删除吗？")){
                //提交表单
                var elementById = document.getElementById("form");
                //调用方法提交
                elementById.submit();
            }
        }

        function selectAll() {
            //全选选中实现
            var elementById = document.getElementById("cb");
            var cbs = document.getElementsByName("ids");
            elementById.onclick = function () {
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">玩家信息列表</h3>
    <div style="float: left;margin: 6px;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">玩家</label>
                <input type="text" class="form-control"  name="username" value="${condition.username}" placeholder="可输入部分账号信息" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">性别</label>
                <input type="text" class="form-control" name="gender" value="${condition.gender}" placeholder="可输入男OR女" id="exampleInputEmail2">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail3">生日</label>
                <input type="text" class="form-control"  name="birthday" value="${condition.birthday}" placeholder="可输入年或月或日" id="exampleInputEmail3">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float:right;margin: 6px">
        <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加玩家</a></td>
        <td colspan="8" align="center"><a class="btn btn-primary" href="javascript:submitIds();">删除选中</a></td>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">

            <tr class="success">
                <th><input type="checkbox" id="cb" onclick="selectAll();"></th>
                <th>编号</th>
                <th>玩家</th>
                <th>性别</th>
                <th>电话</th>
                <th>生日</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="users" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="ids" value="${users.id}"></td>
                    <td>${s.count}</td>
                    <td>${users.username}</td>
                    <td>${users.gender}</td>
                    <td>${users.tell}</td>
                    <td>${users.birthday}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${users.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${users.id});">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage==1}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage!=1}">
                    <li>
                 </c:if>

                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}&rows=5&username=${condition.username}&gender=${condition.gender}&birthday=${condition.birthday}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage==i}">

                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&username=${condition.username}&gender=${condition.gender}&birthday=${condition.birthday}">${i}</a></li>
                    </c:if>

                    <c:if test="${pb.currentPage!=i}">

                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&username=${condition.username}&gender=${condition.gender}&birthday=${condition.birthday}">${i}</a></li>
                    </c:if>

                </c:forEach>
                <c:if test="${pb.currentPage==pb.totalPage}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage!=pb.totalPage}">
                    <li>
                </c:if>

                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}&rows=5&username=${condition.username}&gender=${condition.gender}&birthday=${condition.birthday}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        </a>
                </li>
            </ul>
        </nav>
    </div>
    <span style="margin: 5px;font-size: 15px">
                    总共${pb.totalCount}条记录，${pb.totalPage}页
    </span>
</div>

</body>
</html>
