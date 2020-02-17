package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 激活用户时访问的servlet
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取访问字符串中的Code码
        String code = req.getParameter("code");
        System.out.println("输出来看一下验证码："+code);
        //2.按code码查询是否有该账户，如果有注册彻底成功
        UserServiceImpl userService = new UserServiceImpl();
        boolean flag = userService.active(code);
        //3.输出数据到页面
        resp.setContentType("text/html;charset=utf-8");
        if(flag){
            //成功
            resp.getWriter().write("注册成功！<a href='login.html'>去登陆吧</a>");
        }else{
            //失败
            resp.getWriter().write("注册失败！请重新操作，或者询问管理员！");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
