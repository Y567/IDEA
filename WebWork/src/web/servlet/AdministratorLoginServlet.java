package web.servlet;

import domain.Administrator;
import org.springframework.beans.BeanUtils;
import service.impl.AdministratorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */
@WebServlet("/administratorLoginServlet")
public class AdministratorLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码集
        req.setCharacterEncoding("utf-8");
        //获取数据
        String verifycode = req.getParameter("verifycode");
        //验证码验证
        HttpSession session = req.getSession();
        String checkCode = (String)session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        if(!checkCode.equalsIgnoreCase(verifycode)){
            //验证码错误那么就转发至登陆界面并报错误信息
            req.setAttribute("login_msg","验证码错误");
            req.getRequestDispatcher("/administratorLogin.jsp").forward(req,resp);
            //直接返回，下面的就不需要运行了
            return;
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //封装为管理员对象
        Administrator ad = new Administrator();
        ad.setUsername(username);
        ad.setPassword(password);
        //调用service层查询管理员
        ad = new AdministratorServiceImpl().findAdministrator(ad);
        if(ad != null){
            //将管理员存入session
            session.setAttribute("administrator",ad);
            //找到了这个管理员那么就重定向到管理界面(因为不需要request共享数据了,所以使用重新定向)
            resp.sendRedirect(req.getContextPath()+"/manager.jsp");
        }else{
            //没有找到说明用户名或者密码错误
            req.setAttribute("login_msg","用户名或密码错误");
            req.getRequestDispatcher("/administratorLogin.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
