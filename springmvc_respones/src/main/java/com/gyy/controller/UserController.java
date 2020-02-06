package com.gyy.controller;

import com.gyy.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {


    /**
     * 响应回去一些信息
     */
    @RequestMapping("/testString")
    public String sayHello(Model model){
        //模拟从数据库取到数据
        User user = new User();
        user.setAge(23);
        user.setUsername("猛狗铺盖");
        user.setPassword("yy5201314");
        model.addAttribute("user",user);
        return "success";
    }

    /**
     * 返回值是 void
     * @return
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.0利用转发转发到固定页面，利用转发就不会在走视图解析器，所以需要写出路径
//        req.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(req,resp);

        //2.重定向
        //resp.sendRedirect(req.getContextPath()+"/sendDirect.jsp");

        //3.直接响应
        resp.getWriter().print("sorry");
        return;

    }


    /**
     * 返回值为ModelAndView
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();

        //模拟从数据库取到数据
        User user = new User();
        user.setAge(23);
        user.setUsername("恶犬");
        user.setPassword("ag112233");

        //利用ModelAndView存入数据
        mv.addObject("user",user);

        //设置跳转路径
        mv.setViewName("success");
        return mv;
    }


    /**
     * 测试返回值为关键字的跳转
     * @return
     */
    @RequestMapping("/testKey")
    public String testKey(){
        System.out.println("测试关键字跳转页面");
//        return "forward:/WEB-INF/pages/success.jsp";

        //重定向不用加项目名在这里，因为底层帮你实现了
        return "redirect:/sendDirect.jsp";
    }

    /**
     * 接受异步请求
     * @param
     * @return  @ResponseBody注解：用于将json对象转换为javaBean对象
     */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行了");
        System.out.println(user);
        return user;
    }
}
