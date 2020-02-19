package com.gyy.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
import com.gyy.service.impl.UserServiceImpl;
import com.gyy.util.MailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 做了两件事：
 *  1.第一件：判断数据库中用户输入的邮箱是否存在，存在则注册失败，返回json
 *  2.第二件：如果数据库中不存在该邮箱，则注册成功并向用户发送一份邮件[点击激活用户]
 */
@WebServlet("/emailServlet")
public class EmailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        //1.第一步首先获取表单的数据
        Map<String, String[]> map = req.getParameterMap();
        //2.封装数据
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.先查询数据库中是否有该用户，有则注册失败，无则注册成功
        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.findByEmail(user.getEmail());
        //创建响应对象
        ResultInfo info = null;
        if(u == null){
            //说明数据库中没有数据，那么我们应该在数据库中存入用户信息
            userService.saveUser(user);
            info = new ResultInfo(true);
            //查询出来用户,为了得到那个id
            user = userService.findByEmail(user.getEmail());
            MailUtils.sendMail(user.getEmail(),"<a href='http://127.0.0.1:8080/imageserver2/activeUserServlet?id="+user.getId()+"'>点击激活账户</a>","个人图库");
        }else{
            //注册失败说明数据库中存在此邮箱
            info = new ResultInfo(false,"该邮箱已注册！");
        }
        //4.最后返回数据（利用json返回数据）
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
