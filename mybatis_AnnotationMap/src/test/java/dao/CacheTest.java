package dao;

import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {
    public static void main(String[] args) throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user1 = userDao.findById(7);
        System.out.println(user1);
        //关掉一级缓存
        sqlSession.close();
        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao2 = sqlSession1.getMapper(UserDao.class);
        User user2 = userDao2.findById(7);
        System.out.println(user2);

    }
}
