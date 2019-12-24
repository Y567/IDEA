package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtils {
    private static DataSource ds;

    //利用静态代码块完成数据库连接池的创建
    static{
        //1.获取properties对象
        Properties properties = new Properties();
        //2.加载文件
        try {
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //3.获取连接池
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //获取连接对象的方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //释放资源的方法
    public static void close(ResultSet rs, Statement stmt,Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(Statement stmt,Connection conn){
        close(null,stmt,conn);
    }

    //返回数据库连接池
    public static DataSource getDataSource(){
        return ds;
    }
}
