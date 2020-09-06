package com.gyy.web.filter;

import com.gyy.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //0.强制转换对象
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

//        System.out.println("过滤器拦截了");

        //1.获取访问路径
        String uri = req.getRequestURI();
        //这里再添加一个reference的白名单，让我自己的博客可以不用登录即可使用
        if(uri.contains("index.html") || uri.contains("/user/") || uri.contains("/js/") || uri.contains("/images/") || uri.contains("/css/") || uri.contains("/fonts/")){
            //如果包含这些路径说明用户就是想登录，那么就通行
            chain.doFilter(req,resp);
        }else{
            //如果不包含这些路径，说明可能是偷资源的，决定不能，就要判断用户是否登录了
            User user = (User) req.getSession().getAttribute("user");

            //2.如果不存在或者为空则代表未登录，那么就跳转到登陆界面
            if(user == null){
                resp.sendRedirect(req.getContextPath()+"/index.html");
            }else{
                //3.用户存在说明登录了放行
                chain.doFilter(req,resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
