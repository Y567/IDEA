package util;


import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final String host = "127.0.0.1";
    private static final String username = "root";
    private static final String password = "yy5201314";
    private static final String databaseName = "tangshi";
    private static final int port = 3306;
    private static final String encoding = "utf-8";

    private static volatile MysqlConnectionPoolDataSource dataSource = null;
    //利用单例模式中的懒汉模式保证创建的线程池是线程安全的
    private static MysqlConnectionPoolDataSource getDataSource() {
        if(dataSource == null){
            synchronized (DBUtil.class){
                if(dataSource == null){
                    dataSource = new MysqlConnectionPoolDataSource();
                    dataSource.setServerName(host);
                    dataSource.setDatabaseName(databaseName);
                    dataSource.setUser(username);
                    dataSource.setPassword(password);
                    dataSource.setPort(port);
                    dataSource.setUseSSL(false);
                    dataSource.setCharacterEncoding(encoding);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
             return getDataSource().getConnection();
        }
        return dataSource.getConnection();
    }

    public static void close(Connection con, Statement stmt, ResultSet rs){
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
}
