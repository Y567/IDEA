package com.gyy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器类，需要实现一个接口，该接口可以覆写三个方法
 * 分别再控制器方法执行的前后执行和页面响应之后执行
 */
public class TestInterceptor implements HandlerInterceptor {

    /**
     * 在控制器方法之前执行
     * @param request  可以利用该对象进行页面转发
     * @param response 可以进行重定向
     * @param handler
     * @return return true 表示放行，让用户通过，false表示拦截
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器方法先执行");
        return true;
    }

    /**
     * 在控制器方法执行之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("第一个拦截器方法执行了（后）");
    }

    /**
     * 在页面响应之后最后执行一般用来释放资源
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("第一个拦截器方法在页面响应之后执行了");
    }
}
