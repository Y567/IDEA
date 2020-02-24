package com.gyy.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 自定义的所有servlet的一个基类，我们利用该类的service实现子类的方法分发
 * 实现servlet的抽取，减少servlet，一个user有一个servlet，里面不同的方法代表不同的功能
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.首先我们要获取请求路径  例如：虚拟路径/user/方法名  ?号后面的字符串参数不属于uri
        String uri = req.getRequestURI();

//        System.out.println(uri);
        //2.截取请求路径上包含的将要访问的方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);

        //3.利用this获取子类的class对象，再利用反射获取子类的方法对象
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //4.执行方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 写一个json序列化的方法让子类调用，因为ajax交互较多多，重写代码多可以抽取一下
     * 该方法是以字符串形式返回的
     * @param info
     * @param resp
     * @throws IOException
     */
    public void writeValue(Object info,HttpServletResponse resp) throws IOException {
        //4.响应数据,这里只有登陆失败才会走
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

}
