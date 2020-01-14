import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisDemo1 {
    /**
     * MyBatis的入门案例
     */

    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.根据工厂生产SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用自己实现的dao层
        UserDao userDao = new UserDaoImpl(factory);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        //遍历测试
        for(User user: users){
            System.out.println(user);
        }

        //6.关闭资源
        sqlSession.close();
        in.close();
    }
}
