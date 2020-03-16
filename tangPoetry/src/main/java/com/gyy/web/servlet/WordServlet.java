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
import java.util.Map;

@WebServlet("/wordServlet")
public class WordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //0.响应对象
        ResultInfo info = new ResultInfo();
        //1.获取诗人的名字
        String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
//        System.out.println("查询"+name);
        //2.创建业务层对象
        PoetryServiceImpl poetryService = new PoetryServiceImpl();
        //3.去查询（数据库或者redis）
        Jedis jedis = JedisUtil.getJedis();
        //需要用到的json字符串
        String json;
        if(jedis.get(name)==null){
//            System.out.println("数据库查");
            //3.1去数据库中查
            Map<String,Integer> map = poetryService.findWordsByName(name);
            //4.处理数据
            if(map.size()==0){
                //没有数据
                info.setFlag(false);
                info.setReason("非常抱歉，查不到此诗人的数据");
                json = JsonUtil.toJson(info);
            }else{
                info.setData(map);
                json = JsonUtil.toJson(info);
                //存入redis数据库
                jedis.set(name,json);
            }
        }else{
            //存在数据去redis中查
//            System.out.println("redis中查");
            json = jedis.get(name);
        }
        //5.关掉连接
        JedisUtil.close(jedis);
        //6.写回数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
