package lab;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDemo {
    @Test
    public void dataDemoTest(){
        String dynasty = "唐代";
        String author = "白居易";
        String title = "问刘十九";
        String content = "绿蚁新醅酒，红泥小火炉。晚来天欲雪，能饮一杯无？";
        //1.注册数据库
        //2.通过driverManage获取连接对象
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //String url = "jdbc:mysql://127.0.0.1/tangshi?userSSL=false&characterEncoding=utf-8"

        //通过DataSource获取连接对象 拥有连接池
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setServerName("127.0.0.1");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("yy5201314");
        dataSource.setDatabaseName("tangshi");
        dataSource.setUseSSL(false);
        dataSource.setCharacterEncoding("UTF8");

        Connection con = null;
        PreparedStatement pst = null;
        try {
            //获取连接连接对象
            con = dataSource.getConnection();
            //获取执行对象
            // 定义sql语句
            String sql = "insert into tangshi values(null,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            //设置值
            pst.setString(1,"05e60a7ef9ef8791784b7e59d054f52a1184a331a275ffb2b9b744e86ef98db5");
            pst.setString(2,dynasty);
            pst.setString(3,title);
            pst.setString(4,author);
            pst.setString(5,content);
            pst.setString(6,"分词,洛克人,一杯无");

            //执行语句
            pst.executeUpdate();
        } catch (SQLException e) {
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
        }
    }
}
