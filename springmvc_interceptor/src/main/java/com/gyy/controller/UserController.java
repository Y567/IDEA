package com.gyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试拦截器的控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 拦截的测试方法
     */
    @RequestMapping("/testHandlerInterceptor")
    public String testHandlerInterceptor(){
        System.out.println("拦截测试方法执行了...");
        return "success";
    }
}
