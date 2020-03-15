package com.gyy.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.ResultInfo;
import com.gyy.service.impl.PoetryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/wordServlet")
public class WordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取诗人的名字
        String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
//        System.out.println("查询"+name);
        //2.创建业务层对象
        PoetryServiceImpl poetryService = new PoetryServiceImpl();
        //3.调用方法返回对象
        Map<String,Integer> map = poetryService.findWordsByName(name);
        //4.处理数据
        ResultInfo info = new ResultInfo();
        if(map.size()==0){
            //没有数据
            info.setFlag(false);
            info.setReason("非常抱歉，查不到此诗人的数据");
        }else{
            info.setData(map);
        }

        //5.写回数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
//        System.out.println("数据是"+json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
