package com.gyy.web.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
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
@WebServlet("/loginUserServlet")
public class LoginUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("走到这里来了");
        ResultInfo info = new ResultInfo();
        //1.获取数据然后查询数据库看是否存在该用户
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //2.查询数据
        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.findByEmailAndPassword(email, password);
//        System.out.println(u);
        //3.处理结果，如果为null,说明邮箱或密码不正确
        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("邮箱或密码输入错误");
        }else{
            if(!"Y".equalsIgnoreCase(u.getStatus())){
                //说明该用户并未激活
                info.setFlag(false);
                info.setErrorMsg("该用户未激活");
            }else{
                System.out.println("走到了转发的路径");
                //说明输入成功了，我们直接转发到index.html
                //我们将登陆成功的用户存入session中保证以后查询图片查的是自己的
                req.getSession().setAttribute("user",u);
                req.getRequestDispatcher("/index.html").forward(req,resp);
                return;
            }
        }
        //4.响应数据,这里只有登陆失败才会走
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
