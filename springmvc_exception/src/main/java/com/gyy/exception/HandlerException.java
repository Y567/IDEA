package com.gyy.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器，这个类实现了HandlerExceptionResolver接口用来接受异常并决定异常该往那里去
 */
public class HandlerException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获得异常Exception(这里的Exception其实就是我们自定义的异常，因为再控制器我们写代码将其抛了出来)
        SysException se = null;  //自定义异常
        if(e instanceof SysException){
            se = (SysException) e;
        }else{
            se = new SysException("系统发生异常！");
        }
        //设置ModelAndView的属性将异常存入rrequest去往友好界面展示
        ModelAndView mv = new ModelAndView();

        //存储异常信息
        mv.addObject("errorMsg",se.getMessage());

        //设置要去往的页面
        mv.setViewName("error");

        return mv;
    }
}
