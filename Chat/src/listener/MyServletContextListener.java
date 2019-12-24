package listener;

import domain.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    //监听servletContext对象的创建
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //创建了一个Map集合来存储用户信息（仅此一个map，是公用的）
        Map<User, HttpSession> userMap = new HashMap<>();
        servletContextEvent.getServletContext().setAttribute("userMap",userMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
