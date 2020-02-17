package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    //因为在servlet中业务层操作过多service层所以将该对象设置为成员变量不用重复创建
    private UserServiceImpl userService = new UserServiceImpl();

    public boolean checkCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //先判断验证码是否正确
        String check = req.getParameter("check");
        //从session中获取验证码
        HttpSession session = req.getSession();
        Object checkcode_server = session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只用一次
//        System.out.println("传上来了");
        if(checkcode_server == null || !check.equalsIgnoreCase((String) checkcode_server)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //转json
            String json = writeValueAsString(info, resp);
            resp.getWriter().write(json);
            //记得return
            return false;
        }else{
            return true;
        }
    }
    /**
     * 注册功能
     * @param req
     * @param resp
     */
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkCode(req,resp)){
            return;
        }
        //1.获取前端上传上来的数据
        Map<String, String[]> map = req.getParameterMap();
        //2.创建一个实体类
        User user = new User();
        //3.封装到实体类中
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.创建service实现类进行注册
//        UserServiceImpl userService = new UserServiceImpl();
        //这里返回一个布尔值
        boolean flag = userService.regist(user);
        //5.响应结果，这里专门创建了一个响应结果的实体类进行响应结果的传输
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true); //成功
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败！大人，用户已存在");
        }
        //6.将响应结果返回给前端,将对象转换为json对象
        String json = writeValueAsString(info, resp);
        resp.getWriter().write(json);
    }

    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //前提：进行验证码检验
        if(!checkCode(req,resp)){
            return;
        }
        //响应对象
        ResultInfo info = new ResultInfo();
        //1.从前端获取用户的登录数据
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        //2.根据用户的密码和用户名去查询用户的信息
//        UserServiceImpl userService = new UserServiceImpl();
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
            //获取session对象存入登录用户的信息
            HttpSession session = req.getSession();
            //这里我们将登录的用户存入session中为了首页展示username
            session.setAttribute("user",u);
        }
        //5.响应页面
        String json = writeValueAsString(info, resp);
        resp.getWriter().write(json);
    }

    /**
     * 从session中找到已登录的用户的名字
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.我们可以在session中获取到当前登录的用户
        User user = (User)req.getSession().getAttribute("user");
        //2.将该用户响应回去
        //这是将user对象转换为json对象
        String json = writeValueAsString(user, resp);
        resp.getWriter().write(json);
    }

    /**
     * 退出功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.需要退出的话，我们只需要将session删除就好了。
        req.getSession().invalidate();
        //2.删除后直接重定向到首页
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }

    /**
     * 激活用户功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            resp.getWriter().write("注册成功！<a href='http://127.0.0.1:80/travel/login.html'>去登陆吧</a>");
        }else{
            //失败
            resp.getWriter().write("注册失败！请重新操作，或者询问管理员！");
        }
    }
}
