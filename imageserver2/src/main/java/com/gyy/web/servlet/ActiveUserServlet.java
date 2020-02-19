package com.gyy.web.servlet;
import com.gyy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 激活用户用
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.用户访问到这里说明以及点击了邮件，所以我们需要改变数据库中用户的状态为Y
        UserServiceImpl userService = new UserServiceImpl();
//        System.out.println(req.getParameter("id"));
        userService.activeUser(Integer.parseInt(req.getParameter("id")));
        //2.以及激活成功直接重定向到登录界面(重定向忘记了加斜杠)
        resp.sendRedirect(req.getContextPath()+"/register.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
