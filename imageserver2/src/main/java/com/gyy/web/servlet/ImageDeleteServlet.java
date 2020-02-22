package com.gyy.web.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.Image;
import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
import com.gyy.service.impl.ImageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 查询所有图片使用
 */
@WebServlet("/imageDeleteServlet")
public class ImageDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //走到这里
        System.out.println("走到删除图片了");
        ResultInfo info = new ResultInfo();
        //1.获取当前登录的用户
        User user = ((User)req.getSession().getAttribute("user"));
        //2.获取imageId
        String imageId = req.getParameter("imageId");
        System.out.println(imageId);
        //3.执行方法
        ImageServiceImpl imageService = new ImageServiceImpl();
        //保存一下要删除的图片的MD5（后面决定是否删磁盘文件用到）
        Image image = imageService.findByImageId(Integer.parseInt(imageId),user.getId());
        //这只是删除了数据库中的记录
        boolean flag = imageService.deleteImageByImageId(Integer.parseInt(imageId),user.getId());
        if(flag){
            //删除记录成功
            info.setFlag(true);
            //接下来要删除磁盘中的文件,删除之前再查看数据库中是否还有该图片的记录(优化磁盘)
            //有说明还有用户用它，所以不能删除磁盘的文件
            Image imageByMD5 = imageService.findImageByMD5(image.getMd5(), user.getId());
            if(imageByMD5 == null){
                //只要image为空才能说明磁盘的文件可以删除了
                File file = new File(image.getPath());
                if(file.exists()){
                    //删除磁盘文件
                    file.delete();
                }
            }
        }else{
            //删除记录失败
            info.setFlag(false);
            info.setErrorMsg("因未知原因，没有扔成功!");
        }
        //4.响应数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
