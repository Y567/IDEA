package com.gyy.controller;

import com.gyy.domain.Account;
import com.gyy.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试请求参数绑定的控制器类
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 绑定普通的参数
     */
    @RequestMapping("/testParamDemo")
    public String testParamDemo(String username,String password){
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        return "success";
    }

    /**
     * 绑定javaBean
     * @param account
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println(account);
        return "success";
    }


    /**
     * 自定义类型转换器
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user){
        System.out.println(user);
        return "success";
    }
}
