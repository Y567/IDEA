package web.servlet;

import dao.UserDao;
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
@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码集
        req.setCharacterEncoding("utf-8");
        //1.获取请求数据
        String[] ids = req.getParameterValues("ids");

        //2.调用Service层的方法删除
        UserServiceImpl userService = new UserServiceImpl();
        userService.delUsers(ids);

        //3.删除成功后进行跳转
        resp.sendRedirect(req.getContextPath()+"/findUserByPageServlet");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
