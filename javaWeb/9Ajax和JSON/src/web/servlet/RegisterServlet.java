package web.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("来了");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");

        //1.创建核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        if("gyy".equals(username)){
            //用户已存在
            Map<String,String> map = new HashMap<String,String>();
            map.put("userExist","1");
            map.put("msg","用户已存在了兄弟，换一个吧");

            //转换Map对象为JSon对象
            objectMapper.writeValue(resp.getWriter(),map);
        }else{
            //用户不存在
            Map<String,String> map = new HashMap<String,String>();
            map.put("userExist","0");
            map.put("msg","用户可以用");
            //转换Map对象为JSon对象
            objectMapper.writeValue(resp.getWriter(),map);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}

