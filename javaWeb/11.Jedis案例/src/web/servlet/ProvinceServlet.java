package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Province;
import service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.0查询数据库中的省份
        ProvinceServiceImpl provinceService = new ProvinceServiceImpl();
/*        List<Province> list = provinceService.findAll();

        //2.0将返回的list集合序列化为json对象
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);*/

        //V2 利用redis缓存提高性能
        String json = provinceService.findAllByRedis();
        System.out.println(json);

        //3.0设置响应结果
        //先设置响应编码集
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
