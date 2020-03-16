package com.gyy.web.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.Echart;
import com.gyy.domain.ResultInfo;
import com.gyy.service.impl.PoetryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/echartServlet")
public class EchartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取传上来的参数
        String left = req.getParameter("left");
        String right = req.getParameter("right");
        //2.创建业务层对象
        PoetryServiceImpl poetryService = new PoetryServiceImpl();
        //3.去数据库中查询
        List<Echart> byAmount = poetryService.findByAmount(Integer.parseInt(left),Integer.parseInt(right));
        //4.处理一下数据
        ResultInfo info = new ResultInfo();
        if(byAmount==null || byAmount.size() == 0){
            //说明没有数据
            info.setFlag(false);
            info.setReason("这个区间没有数据哦！");
        }else{
            info.setData(byAmount);
        }
        //5.转换为json对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
