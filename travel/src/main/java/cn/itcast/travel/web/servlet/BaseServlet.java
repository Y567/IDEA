package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 所以自定义servlet的父类在此类的service实现各种功能的方法分用，达到代码抽取的作用
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.先获取访问字符串的路径,注意这里的uri是从虚拟路径到资源路径结束的不包括访问参数
        String uri = req.getRequestURI();
//        System.out.println(uri);

        //2.截取uri中的最后的访问路径即将来要执行的方法
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

        try {
            //3.利用反射获取该方法的Method对象,this其实就是调用该service方法继承BaseServlet的子类
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //4.利用invoke方法执行该方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 考虑到html的Ajax请求会很多，所以频繁的需要json序列化,所以将其抽取到父类中
     * @param object
     * @param resp
     * @return
     */
    public String writeValueAsString(Object object,HttpServletResponse resp) throws JsonProcessingException {
        //1.创建ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //2.设置响应格式
        resp.setContentType("application/json;charset=utf-8");
        //3.利用mapper对象创建序列化
        return mapper.writeValueAsString(object);
    }
}
