package domain;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;
import java.util.Objects;

//使用HttpSessionBindingListener监听session对象的绑定
public class User implements HttpSessionBindingListener {
    private int id;
    private String username;
    private String password;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //该方法再session对象与JavaBean绑定时触发，比如，session.setAttribute(User,"***)时
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("进入了....");
        //通过事件对象获取session对象
        HttpSession session = event.getSession();

        //session对象获取servletContext再通过其获取用户列表
        Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
                .getServletContext().getAttribute("userMap");

        //将该用户放入用户列表
        userMap.put(this, session);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("退出了....");
        //通过事件对象获取session对象
        HttpSession session = event.getSession();

        //session对象获取servletContext再通过其获取用户列表
        Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
                .getServletContext().getAttribute("userMap");

        //将该用户移除出用户列表
        userMap.remove(this);
    }
}
