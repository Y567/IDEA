package web.servlet;

import domain.PageBean;
import domain.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取参数
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        Map<String,String> condition = new HashMap<String,String>();
        condition.put("username",username);
        condition.put("gender",gender);
        condition.put("birthday",birthday);
        //健壮性判断
        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="5";
        }
        //调用Service层方法实现查询
        UserServiceImpl userService = new UserServiceImpl();
        PageBean<User> pb = userService.findUserByPage(Integer.parseInt(currentPage),Integer.parseInt(rows),condition);

 //       System.out.println(pb);
        //将PageBean对象存入request并转发页面进行展示
        req.setAttribute("pb",pb);
        //回显查询条件
        req.setAttribute("condition",condition);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
