import java.sql.*;

public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "yy5201314");
        //3.获取预处理对象
        PreparedStatement pstm = conn.prepareStatement("select * from account");
        //4.执行方法,获取结果集
        ResultSet rs = pstm.executeQuery();
        //5.遍历结果集
        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
        //6.释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}
