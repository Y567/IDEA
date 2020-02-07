package com.gyy.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 传统的文件上传方法
     */
    @RequestMapping("/fileupload")
    public String fileupload(HttpServletRequest request) throws Exception {
        //使用fileupload组件上传文件
        //上传的位置(这里绝对位置，项目部署的绝对位置)
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
//        System.out.println(path);
        //判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //如果不存在则需要创建文件夹
            file.mkdir();
        }

        //创建一个解析器工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建一个解析器
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析上传文件
        List<FileItem> items = upload.parseRequest(request);

        //遍历文件项
        for(FileItem item: items){
            //判断是否为文件项如果为普通表单文件项则不处理
            if(item.isFormField()){
                //普通表单文件项
            }else{
                //获取文件名称
                String filename = item.getName();
                //利用UUID避免文件覆盖（其实就是起一个独一无二的文件名称）
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //进行上传文件
                item.write(new File(path,filename));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }

    /**
     * springmvc的文件上传需要在springmvc文件中配置解析器
     * @param request,upload需要和文件上传表单中的name属性一致
    * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        //使用fileupload组件上传文件
        //上传的位置(这里绝对位置，项目部署的绝对位置)
        String path = request.getSession().getServletContext().getRealPath("/uploads2/");
        //判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //如果不存在则需要创建文件夹
            file.mkdir();
        }

        //二者的区别是springmvc替我们做了解析的工作
        //获取文件名称
        String filename = upload.getOriginalFilename();
        //利用UUID避免文件覆盖（其实就是起一个独一无二的文件名称）
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //进行上传文件,不需要自己进行删除临时文件，springmvc会帮你上传
        upload.transferTo(new File(path,filename));
        return "success";
    }


    /**
     * springmvc实现模拟跨服务器传文件，再创建另一个项目启动一个服务器模拟目标服务器
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload3")
    public String fileupload3(MultipartFile upload) throws Exception {
        //使用fileupload组件上传文件
        //上传的位置(这里绝对位置，项目部署的绝对位置)
        String path = "http://localhost:9090/imageserver/uploads/";

        //获取文件名称
        String filename = upload.getOriginalFilename();
        //利用UUID避免文件覆盖（其实就是起一个独一无二的文件名称）
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //进行上传文件,跨服务器上传,这里需要依赖sun公司提供的jar包实现
        //1.先要创建一个客户端
        Client client = Client.create();

        //2.与目标服务器建立连接
        WebResource resource = client.resource(path+filename);

        //3.上传文件,这里是利用字节的方式上传的
        resource.put(upload.getBytes());

        return "success";
    }
}
