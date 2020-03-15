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
import java.util.List;

@WebServlet("/poetServlet")
public class PoetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要查询的诗人的名字的字的个数
        String name = req.getParameter("name");
        //2.创建业务层对象
        PoetryServiceImpl poetryService = new PoetryServiceImpl();
        //3.调用方法
        List<String> names = poetryService.findPoetByName(Integer.parseInt(name));
        //4.处理数据
        ResultInfo info = new ResultInfo();
        if(names == null){
            info.setFlag(false);
            info.setReason("暂无数据");
        }else{
            info.setData(names);
        }
        //5.写回数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
