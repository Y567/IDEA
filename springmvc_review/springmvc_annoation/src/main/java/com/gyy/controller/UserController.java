package com.gyy.controller;

import com.gyy.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 参数一样的时候
     * @param name   这里建议加上RequestParam注解，增加代码的阅读性
     */
    @RequestMapping("/t1")
    public void test1(@RequestParam("name") String name){
        System.out.println(name);
    }

    @RequestMapping("/t2")
    public void test2(@RequestParam("username") String name){
        System.out.println(name);
    }

    @RequestMapping("/t")
    public void test3(User user){
        System.out.println(user);
    }
}
