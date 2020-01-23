package dao;

import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    private InputStream in;
    private SqlSessionFactory factory;

    @Before  //表示在测试方法之前执行
    public void init() throws IOException {
        //1.加载配置文件获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory工厂对象
        factory = new SqlSessionFactoryBuilder().build(in);
    }


    @After
    public void release() throws IOException {
        if(in != null) {
            in.close();
        }
    }


    @Test
    public void cacheTest(){
        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(5);
        //这是为了关闭一级缓存，不关闭的话第二个创建的sqlSession会从这里面找找不到就是去和数据库交互了，所以应该改变。
        sqlSession1.close();
        System.out.println(user1);

        SqlSession sqlSession2 = factory.openSession();
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = userDao2.findById(5);
        sqlSession2.close();
        System.out.println(user2);

        System.out.println(user1==user2);
    }

}

