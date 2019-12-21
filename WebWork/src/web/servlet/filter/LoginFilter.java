package web.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //0.首先要将servletRequest转换为HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //1.获取URI路径
        String uri = req.getRequestURI();
        System.out.println(uri);
        //2.判断是否登录，登录就放行，否则转发至登录界面
        HttpSession session = req.getSession();
        if(uri.contains("/index")||uri.contains("/login")||uri.contains("/eroll")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/img/")||uri.contains("/check")){
            //用户要登陆
            filterChain.doFilter(servletRequest,servletResponse); //放行需要用到的资源
        }else{
            //验证用户是否登录
            Object user = session.getAttribute("user");
            if(user==null) {
                session.setAttribute("login_error","您未登录，请进行登录");
                req.getRequestDispatcher("/index.jsp").forward(servletRequest,servletResponse);
            }else{
                //登录成功
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }
    @Override
    public void destroy() {

    }
}
