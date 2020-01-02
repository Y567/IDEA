package dao.impl;

import dao.UserDao;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public List<User> findAll() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<User>();
        try{
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接对象
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven", "root", "yy5201314");

            //获取执行对象
            pst = con.prepareStatement("select * from user");

            //执行语句
            rs = pst.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                list.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pst != null){
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
