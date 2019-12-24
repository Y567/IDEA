<%@page import="service.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background: linear-gradient(135deg,#56c8ff,#6f99fc) no-repeat;">
    <tr><td height="32" align="center" class="word_orange ">欢迎来到吐槽聊天室！</td></tr>
    <tr>
        <td height="23" align="center"><a href="#" onclick="set('所有人')">所有人</a></td>
    </tr>
    <c:forEach var="entry" items="${ userMap }">
        <tr>
            <td height="23" align="center">
                <a href="#" onclick="set('${ entry.key.username }')">${ entry.key.username }</a>
            <%--    只有管理员（当前存在的用户是管理员）可以踢人，且map集合中不是管理员的才会有踢下线的标志--%>
                <c:if test="${ existUser.type == 'admin' and entry.key.type != 'admin'}">
                    <a href="${ pageContext.request.contextPath }/user?method=kick&id=${ entry.key.id }">踢下线</a>
                </c:if>

            </td>
        </tr>
    </c:forEach>
    <tr><td height="30" align="center">当前在线[<font color="#FF6600">${ fn:length(userMap) }</font>]人</td></tr>
</table>