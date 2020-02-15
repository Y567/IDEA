package com.gyy.util;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql:///image_server?characterEncoding=UTF-8&openSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yy5201314";

    private static volatile DataSource dataSource = null;

    //获取dataSource
    public static DataSource getDataSource(){
        if(dataSource == null){
            synchronized (DBUtil.class){
                if(dataSource == null){
                    dataSource = new MysqlDataSource();
                    MysqlDataSource tempDataSource = (MysqlDataSource) dataSource;
                    tempDataSource.setUrl(URL);
                    tempDataSource.setUser(USERNAME);
                    tempDataSource.setPassword(PASSWORD);
                }
                return dataSource;
            }
        }
        return dataSource;
    }


    //获取连接对象
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //释放资源
    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(pstmt != null){
                pstmt.close();
            }
            if(con != null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
