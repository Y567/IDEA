package 多态;

public class MysqlSon extends MySql {
    public synchronized int getPooledConnection() {
        System.out.println("线程安全");
        int connection = this.getConnection();
        return connection;
    }

    public static void main(String[] args) {
        MysqlSon mysqlSon = new MysqlSon();
        System.out.println(mysqlSon.getPooledConnection());
    }
}
