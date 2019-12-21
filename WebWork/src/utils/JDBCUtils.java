package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    /**
     * 利用工具类实现简化代码的作用：
     *    因为获取连接数据库对象和释放资源的代码是相同的
     *    所以我们将它抽取到一个工具类中，简化以后的代码
     */
    private static String url;
    private static String user;
    private static String password;

    //想要只注册一次就可以，通过静态代码块实现
    static{
        //创建Properties对象读取配置文件
        Properties properties = new Properties();
        //需要获取配置文件的路径，并且获取其数据(类加载器实现)
        ClassLoader classLoader = JDBCUtils.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        //注册驱动(在静态代码块中完成)
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //获取数据库连接对象
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //释放资源
    public static void close(ResultSet rs,Statement stmt, Connection con){
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
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt,Connection con){
        close(null,stmt,con);
    }
}
