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
 *  实现添加玩家信息功能
 */
@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码集
        req.setCharacterEncoding("utf-8");
        //1.获取表单请求的信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String tell = req.getParameter("tell");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        //2.封装成对象(笨方法封装信息)
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTell(tell);
        user.setGender(gender);
        user.setBirthday(birthday);
        //3.调用service层
        UserServiceImpl userService = new UserServiceImpl();
        userService.addUser(user);

        //4.完成以后跳转到展示界面(重定向)
        resp.sendRedirect(req.getContextPath()+"/findUserByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
