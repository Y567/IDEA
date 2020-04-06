package com.gyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestFulTestController {


    @GetMapping("/restful/{a}/{b}")   //规定只能以Get方式去请求,默认提交的是get请求
    public String add(@PathVariable int a, @PathVariable int b, Model model){
        int rest = a + b;
        model.addAttribute("msg","增加为"+rest);
        return "hello";
    }


    @PostMapping("/restful/{a}/{b}")   //规定只能以Get方式去请求
    public String update(@PathVariable int a, @PathVariable int b, Model model){
        int rest = a + b;
        model.addAttribute("msg","更新为"+rest);
        return "hello";
    }

    @RequestMapping("/testView")
    public String test(Model model){
        model.addAttribute("msg","这是一个转发");
        return "/WEB-INF/pages/hello.jsp";
    }

    @RequestMapping("/testView2")
    public String test2(Model model) {
        model.addAttribute("msg", "这是一个重定向");
        return "redirect:/index.jsp";
    }
}
