package com.gyy.controller;

import com.gyy.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用来模拟异常处理
 */
@Controller
@RequestMapping("exception")
public class SysController {

    /**
     * 异常处理方法
     */
    @RequestMapping("sysException")
    public String sysException() throws SysException {
        //模拟异常的情况
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("0不能作为除数！");
        }
        return "success";
    }
}
