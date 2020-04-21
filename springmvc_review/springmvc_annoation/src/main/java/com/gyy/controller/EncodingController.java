package com.gyy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encoding")
public class EncodingController {

    @RequestMapping("/test1")
    public String test1(String name, Model model){
        model.addAttribute("msg",name);
        return "hello";
    }
}
