package com.gyy.web.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.Image;
import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
import com.gyy.service.impl.ImageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有图片使用
 */
@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //1.获取当前登录的用户
        User user = ((User)req.getSession().getAttribute("user"));
        ImageServiceImpl imageService = new ImageServiceImpl();
        //2.查询当前登录用户的所有图片
        List<Image> images = imageService.findAll(user.getId());
//        System.out.println(images);
        //3.以json对象返回数据
        info.setFlag(true);
        info.setData(images);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
//        System.out.println(json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
