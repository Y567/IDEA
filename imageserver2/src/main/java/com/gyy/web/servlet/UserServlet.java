package com.gyy.web.servlet;

import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
import com.gyy.service.impl.UserServiceImpl;
import com.gyy.util.MailUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个servlet完成了所有的关于用户的功能处理
 * 拦截所有的user访问路径
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    /**
     * 用户注册时发送邮件
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void email(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建响应对象
        ResultInfo info = new ResultInfo();
        User user = new User();
        //1.第一步首先获取表单的数据
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = DigestUtils.md5Hex(req.getParameter("password"));
        //2.封装数据
        user.setUsername(username);
        user.setEmail(email);
        //2.1这里用MD5存储密码会更安全
        user.setPassword(password);
        //3.先查询数据库中是否有该用户，有则注册失败，无则注册成功
        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.findByEmail(user.getEmail());
        if(u == null){
            //说明数据库中没有数据，那么我们应该在数据库中存入用户信息
            userService.saveUser(user);
            info.setFlag(true);
            //查询出来用户,为了得到那个id
//            user = userService.findByEmail(user.getEmail());
//            MailUtils.sendMail(user.getEmail(),"<a href='http://127.0.0.1:8080/imageserver2/user/active?id="+user.getId()+"'>点击激活账户</a>","个人图库");
//            MailUtils.sendMail(user.getEmail(),"<a href='http://106.54.208.39:8080/imageserver/user/active?id="+user.getId()+"'>点击激活账户</a>","个人图库");
            //取消了邮箱激活，因为腾讯云25端口没有解封

        }else{
            //注册失败说明数据库中存在此邮箱
            info.setFlag(false);
            info.setErrorMsg("该邮箱已注册！");
        }
        //4.最后返回数据（利用json返回数据）
        writeValue(info,resp);
    }

    /**
     * 激活用户，将数据库中用户的状态改为Y
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1.用户访问到这里说明以及点击了邮件，所以我们需要改变数据库中用户的状态为Y
        UserServiceImpl userService = new UserServiceImpl();
//        System.out.println(req.getParameter("id"));
        userService.activeUser(Integer.parseInt(req.getParameter("id")));
        //2.以及激活成功直接重定向到登录界面(重定向忘记了加斜杠)
        resp.sendRedirect(req.getContextPath()+"/index.html");
    }

    /**
     * 用户激活邮件登录时访问
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("走到这里来了");
        ResultInfo info = new ResultInfo();
        //1.获取数据然后查询数据库看是否存在该用户
        String email = req.getParameter("email");
        //1.1要以MD5去查询数据库
        String password = DigestUtils.md5Hex(req.getParameter("password"));
//        System.out.println(password);
        //2.查询数据
        UserServiceImpl userService = new UserServiceImpl();
        User byEmail = userService.findByEmail(email);
        if(byEmail == null){
            //说明用户就不存在
            info.setFlag(false);
            info.setErrorMsg("用户不存在");
            writeValue(info,resp);
            return;
        }
        User u = userService.findByEmailAndPassword(email, password);
//        System.out.println(u);
        //3.处理结果，如果为null,说明邮箱或密码不正确
        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("密码输入错误");
        }else{
            if(!"Y".equalsIgnoreCase(u.getStatus())){
                //说明该用户并未激活
                info.setFlag(false);
                info.setErrorMsg("该用户未激活");
            }else{
//                System.out.println("走到了转发的路径");
                //说明输入成功了，我们直接转发到index.html（发现的bug:这里不能转发和ajax冲突）
                //我们将登陆成功的用户存入session中保证以后查询图片查的是自己的
                req.getSession().setAttribute("user",u);
                info.setFlag(true);
            }
        }
        //4.响应数据
        writeValue(info,resp);
    }

    /**
     * 退出登录用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除session中的用户,之后过滤器会检查
        req.getSession().removeAttribute("user");
    }
}
