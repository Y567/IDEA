package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录时访问的servlet
 */
@WebServlet("/loginUserServlet")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //前提：进行验证码检验
        //先判断验证码是否正确
        String check = req.getParameter("check");
        //从session中获取验证码
        HttpSession session = req.getSession();
        Object checkcode_server = session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只用一次
        if(checkcode_server == null || !check.equalsIgnoreCase((String) checkcode_server)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //转json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //字符流传输回前端
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            //直接返回避免查询数据库操作提高效率
            return;
        }
        //响应对象
        ResultInfo info = new ResultInfo();
        //1.从前端获取用户的登录数据
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        //2.根据用户的密码和用户名去查询用户的信息
        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.login(user);
        //3.判断用户是否存在
        if(u == null){
            //用户不存在则返回响应信息
            info.setFlag(false);
            info.setErrorMsg("很抱歉，您的用户名或者密码错误！");
        }else if(!"Y".equalsIgnoreCase(u.getStatus())){
            //说明用户存在，但是激活码未开启，返回错误信息
            info.setFlag(false);
            info.setErrorMsg("很抱歉，您的账户未激活，不能登录！");
        }else{
            //走到这里说明数据正确可进行登录
            info.setFlag(true);
            //这里我们将登录的用户存入session中为了首页展示username
            session.setAttribute("user",u);
        }
        //5.响应页面
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        //将响应对象转换器为json对象进行传输
        String json = mapper.writeValueAsString(info);
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
