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
import java.util.Date;
import java.util.List;

public class UserTest {
    private SqlSession sqlSession;
    private InputStream in;
    private UserDao userDao;

    //赋值语句
    @Before
    public void init() throws IOException {
        //1.加载配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.利用输入流创建工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.利用工厂创建sqlSession对象
        sqlSession = factory.openSession();
        //4.生成Dao接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    //释放资源
    @After
    public void destroy() throws IOException {
        if (in != null) {
            in.close();
        }
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testSelect() {
        List<User> users = userDao.findAll();
        for (User u : users) {
            System.out.println("每个用户的信息");
            System.out.println(u);
            System.out.println(u.getAccounts());
        }
    }


    @Test
    public void testFindById() {
        User user = userDao.findById(4);
        System.out.println(user);
    }
}
