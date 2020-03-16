package com.gyy.web.servlet;

import com.gyy.domain.ResultInfo;
import com.gyy.service.impl.PoetryServiceImpl;
import com.gyy.util.JedisUtil;
import com.gyy.util.JsonUtil;
import redis.clients.jedis.Jedis;

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
        ResultInfo info = new ResultInfo();
        //1.获取要查询的诗人的名字的字的个数
        String name = req.getParameter("name");
        //2.创建业务层对象
        PoetryServiceImpl poetryService = new PoetryServiceImpl();
        //3.去查询(数据库中或者redis中)
        Jedis jedis = JedisUtil.getJedis();
        //需要用到的json字符串
        String json;
        if(jedis.get(name)==null){
//            System.out.println("mysql中查诗人");
            List<String> names = poetryService.findPoetByName(Integer.parseInt(name));
            //4.处理数据
            if(names == null){
                info.setFlag(false);
                info.setReason("暂无数据");
                json = JsonUtil.toJson(info);
            }else{
                info.setData(names);
                json = JsonUtil.toJson(info);
                jedis.set(name,json);
            }
        }else{
            //去redis中查
            json = jedis.get(name);
//            System.out.println("redis中查诗人");
        }
        //5.关闭资源
        JedisUtil.close(jedis);
        //6.写回数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
