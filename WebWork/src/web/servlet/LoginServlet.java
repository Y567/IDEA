package web.servlet;

import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码集
        req.setCharacterEncoding("utf-8");
        //获取提交的表单
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");
        //通过Session对象获取验证码
        HttpSession session = req.getSession();
        Object checkCode1 = session.getAttribute("checkCode");
        //一获得就删除
        session.removeAttribute("checkCode");
        //先判断验证码的正确，可以减少访问数据库的次数
        if(checkCode1!=null&&((String)checkCode1).equalsIgnoreCase(checkCode)){
            //这里进来后，说明验证码正确，所以就要访问数据库了

            //1.获取数据库连接对象
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            //2.写sql语句
            //存在SQL注入文题
//            String sql = "select * from user where username='"+username+"' and password='"+password+"'";
            //改进
            String sql = "select * from user where username = ? and password= ?";
            //3.获取sql执行对象
            try {
                con = JDBCUtils.getConnection();
                pstmt = con.prepareStatement(sql);
                //4.执行语句
                //执行前一定记得设置值
                pstmt.setString(1,username);
                pstmt.setString(2,password);
                rs = pstmt.executeQuery();
                if(rs.next()){
                    //说明查到了该用户
                    //登录成功，利用Session对象保存用户信息
                    session.setAttribute("user",username);
                    //重定向到登陆成功界面
                    resp.sendRedirect(req.getContextPath()+"/success.jsp");
                }else {
                    session.setAttribute("login_error", "用户名或密码错误");
                    //请求转发到登录界面
                    resp.sendRedirect(req.getContextPath()+"/index.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //5.释放资源
                JDBCUtils.close(rs,pstmt,con);
            }
        }else{
            //利用转发到登录界面
            //session保存错误信息
            session.setAttribute("cc_error","验证码错误");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
