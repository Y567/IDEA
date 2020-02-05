package com.gyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//控制器类
@Controller
@RequestMapping(path = "/user")  //放在类上面可以作为一级访问目录
public class HelloController {

    //方法
    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("你好啊spring mvc");
        return "success";  //要跳转的页面
    }

    //testRequestMapping
    @RequestMapping(value = "/testRequestMapping",method = {RequestMethod.GET},params = {"username"})
    public String testRequestMapping(){
        System.out.println("RequestMapping注解的测试。。。。");
        return "success";
    }
}
