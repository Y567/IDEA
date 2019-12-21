package web.servlet;

import domain.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取id值
        String id = req.getParameter("id");
        //2.调用Service层的查询方法按id值查询数据
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.findUserById(Integer.parseInt(id));

        //3.将封装好的user对象存入request中
        req.setAttribute("user",user);

        //4.转发到修改页面并在页面中回显数据
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
