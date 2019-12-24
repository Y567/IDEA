package web.servlet;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.BaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/user")
//这里我们不再继承HttpServlet而是利用BaseServlet实现中转的功能，可以让它实现转发至多个页面的功能
public class UserServlet extends BaseServlet {
    /**
     * 检查用户是否被踢下线
     */
    public String check(HttpServletRequest req, HttpServletResponse resp){
        User existUser = (User) req.getSession().getAttribute("existUser");
        if(existUser == null){
            //用户身份过期了
            try {
                resp.getWriter().println("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //不过期
            try {
                resp.getWriter().println("2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 退出
     */

    public String exit(HttpServletRequest req, HttpServletResponse resp){
        req.getSession().invalidate();
        //重定向
        try {
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送聊天的内容
     */
    public String sendMessage(HttpServletRequest req, HttpServletResponse resp){
        // 1.接收数据 。
        System.out.println("sendMessage invoke....");
        String from = req.getParameter("from"); // 发言人
        String face = req.getParameter("face"); // 表情
        String to = req.getParameter("to"); // 接收者
        String color = req.getParameter("color"); // 字体颜色
        String content = req.getParameter("content"); // 发言内容
        // 发言时间 正常情况下使用SimpleDateFormat
        String sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); // 发言时间
        // 获得ServletContext对象.
        ServletContext application = getServletContext();
        //  从ServletContext中获取消息
        String sourceMessage = application.getAttribute("message").toString();
        // 拼接发言的内容:xx 对 yy 说 xxx
        sourceMessage += "<font color='blue'><strong>" + from
                + "</strong></font><font color='#CC0000'>" + face
                + "</font>对<font color='green'>[" + to + "]</font>说："
                + "<font color='" + color + "'>" + content + "</font>（"
                + sendTime + "）<br>";
        // 将消息存入到application的范围
        application.setAttribute("message", sourceMessage);
        return getMessage(req, resp);
    }

    /**
     * 聊天公告
     */
    public String getMessage(HttpServletRequest req, HttpServletResponse resp){
        String message = getServletContext().getAttribute("message").toString();
        if(message != null){
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 踢人功能
     */
    public String kick(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        //获取userMap对象找到对应的用户将其session销毁
        Map<User,HttpSession> userMap = (Map<User, HttpSession>) getServletContext().getAttribute("userMap");
        User user = new User();
        user.setId(id);
        HttpSession session = userMap.get(user);
        session.invalidate();
        userMap.remove(user);
        //消除完以后就重定向回main.jsp
        try {
            resp.sendRedirect(req.getContextPath()+"/main.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 登录功能
     * @param req
     * @param resp
     * @return
     */
    public String login(HttpServletRequest req, HttpServletResponse resp){
        User user = new User();
        //开始封装数据
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        //调用login方法
        UserService userService = new UserServiceImpl();
        User existUser = userService.login(user);

        if(existUser == null){
            System.out.println("登录失败啦");
            //登录失败
            req.setAttribute("msg","用户名或密码错误");
            return "/index.jsp";
        }else{
            // 用户登录成功
            // 第一个BUG的解决:第二个用户登录后将之前的session销毁!
            req.getSession().invalidate();
            // 第二个BUG的解决:判断用户是否已经在Map集合中,存在：已经在列表中.销毁其session.
            // 获得到ServletCOntext中存的Map集合.
            Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext()
                    .getAttribute("userMap");
            // 判断用户是否已经在map集合中'
            if(userMap.containsKey(existUser)){
                // 说用map中有这个用户.
                HttpSession session = userMap.get(existUser);
                // 将这个session销毁.
                session.invalidate();
            }
            //登录成功,用Session保存用户信息
            req.getSession().setAttribute("existUser",existUser);

            ServletContext application = getServletContext();

            String sourceMessage = "";

            if (null != application.getAttribute("message")) {
                sourceMessage = application.getAttribute("message")
                        .toString();
            }
            //第一次登录,有个公告去通知
            sourceMessage += "系统公告：<font color='gray'>"
                        + existUser.getUsername() + "加入聊天室！</font><br>";

            application.setAttribute("message", sourceMessage);

            try {
                resp.sendRedirect(req.getContextPath() + "/main.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
