package com.gyy.web.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.Image;
import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
import com.gyy.service.ImageService;
import com.gyy.service.impl.ImageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 以字节流的形式输出图片到浏览器
 */
@WebServlet("/imageShowServlet")
public class ImageShowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //先获取当前的用户id
        User user = (User)(req.getSession().getAttribute("user"));
        //1.获取请求参数的imageId,根据该参数查询图片
        String imageId = req.getParameter("imageId");
        System.out.println("imageId:"+imageId);
        if(imageId == null || "".equals(imageId)){
            //没有改imageId的值就无法访问
            info.setFlag(false);
            info.setErrorMsg("无法访问");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        ImageService imageService = new ImageServiceImpl();
        Image image = imageService.findByImageId(Integer.parseInt(imageId), user.getId());
        //输出字节流到浏览器
        File file = new File(image.getPath());
        byte[] buffer = new byte[1024];
        //1.创建输入流，用于写入缓冲区
        FileInputStream in = new FileInputStream(file);
        //2.创建输出流，用于输出数据
        //2.1这里记得设置类型，否则会显示失败
        resp.setContentType(image.getContentType());
        ServletOutputStream out = resp.getOutputStream();
        while(true){
            int len = in.read(buffer);
            if(len == -1){
                //数据读取完毕
                break;
            }else{
                out.write(buffer);
            }
        }
        //记得关闭
        in.close();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
