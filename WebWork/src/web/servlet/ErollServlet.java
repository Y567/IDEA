package web.servlet;

import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * 该servlet实现将注册信息存入数据库
 */
@WebServlet("/erollServlet")
public class ErollServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取请求体的用户注册信息数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String tell = req.getParameter("tell");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        //将其存入数据库表
        //1.获取数据库连接对象
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //2.写sql语句
        //2.1先写一个查询语句，检查该用户是否存在
        String sqlcheck = "select * from user where username=?";
        String sql = "insert into user values(null,?,?,?,?,?)";
        //3.获取执行sql语句的对象
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sqlcheck);
            //设置参数
            pstmt.setString(1,username);
            //4.执行语句
            rs = pstmt.executeQuery();
            if(rs.next()){
                //用户已存在
                resp.sendRedirect(req.getContextPath()+"/erollFail.jsp");
                //这里直接返回
                return;
            }
            pstmt = con.prepareStatement(sql);
            //3.1设置参数值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,tell);
            pstmt.setString(4,gender);
            pstmt.setString(5,birthday);
            int i = pstmt.executeUpdate();
            if(i > 0){
                //重定向到别的页面（注册成功），这里就重定向会登录界面让用户登录
                resp.sendRedirect(req.getContextPath()+"/erollSuccess.jsp");
            }else{
                //请求转发到错误页面
//                resp.sendRedirect(req.getContextPath()+"/erollFail.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.释放资源
            JDBCUtils.close(rs,pstmt,con);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
