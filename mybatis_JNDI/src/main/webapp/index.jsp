<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="dao.UserDao" %>
<%@ page import="domain.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 可乐yue
  Date: 2020/1/22
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JNDI</title>
</head>
<body>
hello JNDI
<%
    InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(in);
    SqlSession sqlSession = factory.openSession();
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    List<User> users = userDao.findAll();
    for(User user: users){
        System.out.println(user);
    }
    sqlSession.close();
    in.close();
%>
</body>
</html>
