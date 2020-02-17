package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用Service层代码进行查询
        List<Category> cs = categoryService.findAll();
        //2.将该集合以json对象返回前端
        String json = writeValueAsString(cs, resp);
        resp.getWriter().write(json);
    }
}
