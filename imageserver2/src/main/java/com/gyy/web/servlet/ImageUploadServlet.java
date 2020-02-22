package com.gyy.web.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.Image;
import com.gyy.domain.ResultInfo;
import com.gyy.domain.User;
import com.gyy.service.ImageService;
import com.gyy.service.impl.ImageServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 上传文件使用
 */
@WebServlet("/imageUploadServlet")
public class ImageUploadServlet extends HttpServlet {

    /**
     * 兼容性处理针对不同浏览器比如IE和Google，IE会将图片的绝对路径当作文件名字
     * 这时候去上传到服务器的时候文件就会创建失败，所以我们需要处理
     * @param name
     * @return
     */
    private String parseFileName(String name){
        if(name.contains(":")){
            //说明上传的图片中的名字存在路径.我们要去掉,一定要+1否则还是错的
            return name.substring(name.lastIndexOf("\\")+1);
        }else{
            //没有的话就直接返回去就可以
            return name;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //1.做准备工作,获得upload对象，该对象可以解析请求
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items;
//        System.out.println("上传文件");
        try {
            //1.1解析请求,获得文件项列表
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
            //解析请求对象失败
            info.setFlag(false);
            info.setErrorMsg("解析文件失败，请您重新上传");
            //返回json对象
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }

        for(FileItem item:items) {
            //2.封装Image对象
            Image image = new Image();
            //2.1获取session中的登录信息
            User user = (User) (req.getSession().getAttribute("user"));
            image.setUid(user.getId());

            //2.2格式化上传时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            image.setUploadTime(format.format(new Date()));

            //2.3获取文件的名字
            image.setImageName(parseFileName(item.getName()));
            image.setContentType(item.getContentType());

            //2.4利用md5，用图片的二进制数据进行求MD5加密码，将来存储一样的图片时可以节省磁盘空间
            image.setMd5(DigestUtils.md2Hex(item.get()));

            //2.5设置路径,用MD5可以防止覆盖图片
            String path = req.getSession().getServletContext().getRealPath("/images/"+image.getMd5()+image.getImageName());
            image.setPath(path);
            image.setSize((int) item.getSize());

            //3.先写入磁盘（在写入前会查询数据库是否有数据，无则说明第一次存储图片，可以写入磁盘）
            ImageService imageService = new ImageServiceImpl();
            //3.1获取项目的部署路径
//            System.out.println("图片的路径"+image.getPath());
            File file = new File(image.getPath());
//            System.out.println(image.getPath());
            try {
                //再写入磁盘之前我们先查询一下数据库中是否有了该图片
                Image imageByMD5 = imageService.findImageByMD5(image.getMd5(), user.getId());
                System.out.println("优化磁盘的时候怎么了？");
                if(imageByMD5 == null){
                    //数据库中没有该图片，说明是第一次存储，这时候我们才写入磁盘
                    item.write(file);
                }
                //4.不管磁盘写不写入，数据库中的记录都要写进去
                imageService.insertImage(image);
                //这里我们可以重定向刷新页面
                resp.sendRedirect("index.html");
            } catch (Exception e) {
                e.printStackTrace();
                //写入磁盘出错
                info.setFlag(false);
                info.setErrorMsg("写入文件失败，请您重新上传");
                //返回json对象
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(info);
                resp.setContentType("application/json;charset=utf-8");
                resp.getWriter().write(json);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
