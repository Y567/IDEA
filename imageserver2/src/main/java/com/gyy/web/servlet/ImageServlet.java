package com.gyy.web.servlet;

import com.gyy.domain.Image;
import com.gyy.domain.PageBean;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片相关操作
 */
@WebServlet("/image/*")
public class ImageServlet extends BaseServlet {

    /**
     * 查询所有图片
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //1.获取当前登录的用户
        User user = ((User)req.getSession().getAttribute("user"));
        ImageServiceImpl imageService = new ImageServiceImpl();
        //2.查询当前登录用户的所有图片
        List<Image> images = imageService.findAll(user.getId());
//        System.out.println(images);
        //3.以json对象返回数据
        info.setFlag(true);
        info.setData(images);
        /*ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
//        System.out.println(json);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);*/

        writeValue(info,resp);
    }

    /**
     * 删除图片
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //走到这里
//        System.out.println("走到删除图片了");
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
            info.setErrorMsg("因神秘力量干扰,没有扔成功!");
        }
        //4.响应数据
        /*ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);*/

        writeValue(info,resp);
    }

    /**
     * 以字节流的形式输出图片到浏览器
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //先获取当前的用户id
        User user = (User)(req.getSession().getAttribute("user"));
        //1.获取请求参数的imageId,根据该参数查询图片
        String imageId = req.getParameter("imageId");
//        System.out.println("imageId:"+imageId);
        if(imageId == null || "".equals(imageId)){
            //没有改imageId的值就无法访问
            info.setFlag(false);
            info.setErrorMsg("因神秘力量干扰,无法访问");
            writeValue(info,resp);
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

    /**
     * 这里上传图片会用到文件名解析
     * 1.兼容性处理针对不同浏览器比如IE和Google，IE会将图片的绝对路径当作文件名字
     * 2.这时候去上传到服务器的时候文件就会创建失败，所以我们需要处理
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

    /**
     * 上传图片的处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            info.setErrorMsg("因神秘力量干扰，解析文件失败");
            //返回json对象
            writeValue(info,resp);
            /*ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);*/
            return;
        }

        for(FileItem item:items) {
            if(!item.getContentType().contains("image")){
                //说明用户上传的不是图片，需要告诉用户
                info.setFlag(false);
                info.setErrorMsg("神秘力量阻止你上传其他类型文件");
                writeValue(info,resp);
                return;
            }
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
//                System.out.println("优化磁盘的时候怎么了？");
                if(imageByMD5 == null){
                    //数据库中没有该图片，说明是第一次存储，这时候我们才写入磁盘
                    item.write(file);
                }
                //4.不管磁盘写不写入，数据库中的记录都要写进去
                imageService.insertImage(image);
                //这里我们可以重定向刷新页面
                resp.sendRedirect(req.getContextPath()+"/home.html");
            } catch (Exception e) {
                e.printStackTrace();
                //写入磁盘出错
                info.setFlag(false);
                info.setErrorMsg("因神秘力量干扰,写入文件失败");
                //返回json对象
                writeValue(info,resp);
                /*ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(info);
                resp.setContentType("application/json;charset=utf-8");
                resp.getWriter().write(json);*/
            }
        }
    }


    /**
     * 这是分页查询时访问
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这里可能客户端传来的数据是中文，所以记得要设计编码
        req.setCharacterEncoding("utf-8");

        ResultInfo info = new ResultInfo();
        //获得当前页码
        String currentPage = req.getParameter("currentPage");

        //获取模糊查询时用到的数据
        String imageName = req.getParameter("imageName");
        String uploadTime = req.getParameter("uploadTime");

    /*    System.out.println("名字"+imageName);
        System.out.println("上传时间"+uploadTime);*/

        Map<String,String> condition = new HashMap<String,String>();
        condition.put("imageName",imageName);
        condition.put("uploadTime",uploadTime);

        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }

        //1.获取当前登录的用户
        User user = ((User)req.getSession().getAttribute("user"));
//        System.out.println(user);
        ImageServiceImpl imageService = new ImageServiceImpl();

        //2.调用service层的方法去查询数据，返回的数据是集合
        PageBean<Image> pb = imageService.findByPage(Integer.parseInt(currentPage),15,condition,user.getId());
//        System.out.println("分页查询的结果:"+pb);
        if(pb.getData() == null || pb.getData().size() == 0){
            //如果这里为0里我们就给他一次机会，因为还有可能用户从第二页删除到了第一页，导致刷新第二页的时候查到的为空
            //但是不代表真的为空，我们可以去查前一页的内容
            pb = imageService.findByPage(Integer.parseInt(currentPage)-1,15, condition, user.getId());
        }
        info.setFlag(true);
        info.setData(pb);
//        System.out.println("测试"+pb.getData().size());
        //3.响应数据回前端
        writeValue(info,resp);
    }
}
