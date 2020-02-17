package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 注册时访问的servlet
 */
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先判断验证码是否正确
        String check = req.getParameter("check");
        //从session中获取验证码
        HttpSession session = req.getSession();
        Object checkcode_server = session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只用一次
        System.out.println("传上来了");
        if(checkcode_server == null || !check.equalsIgnoreCase((String) checkcode_server)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //转json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //字符流传输回前端
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            //记得return
            return;
        }

        //1.获取前端上传上来的数据
        Map<String, String[]> map = req.getParameterMap();
        //2.创建一个实体类
        User user = new User();
        //3.封装到实体类中
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.创建service实现类进行注册
        UserServiceImpl userService = new UserServiceImpl();
        //这里返回一个布尔值
        boolean flag = userService.regist(user);
        //5.响应结果，这里专门创建了一个响应结果的实体类进行响应结果的传输
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true); //成功
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败！大人，用户已存在");
        }
        //6.将响应结果返回给前端,将对象转换为json对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //字符流传输回前端
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
