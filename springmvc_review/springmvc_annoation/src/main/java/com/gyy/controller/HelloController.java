package com.gyy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")  //确定唯一的controller
public class HelloController {

    @RequestMapping("/first")   //确定要访问的方法
    public String sayHello(Model model){
        model.addAttribute("msg","阿狗牛逼");
        return "hello";
    }
}
