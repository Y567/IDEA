package web.servlet;


import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  实现删出玩家信息功能
 */
@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取删除的id
        String id = req.getParameter("id");

        //2.调用Service层的删除方法进行删除
        UserServiceImpl userService = new UserServiceImpl();
        userService.deleteUserById(Integer.parseInt(id));

        //3.完成以后跳转到展示界面(重定向)
        resp.sendRedirect(req.getContextPath()+"/findUserByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
