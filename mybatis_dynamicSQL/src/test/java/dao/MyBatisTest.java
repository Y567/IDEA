package dao;

import domain.QueryVo;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.management.Query;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.deploy.util.SearchPath.findOne;

public class MyBatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before  //表示在测试方法之前执行
    public void init() throws IOException {
        //1.加载配置文件获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void release() throws IOException {

        //事务提交
        sqlSession.commit();
        //6.释放资源
        if(sqlSession != null){
            sqlSession.close();
        }
        if(in != null){
            in.close();
        }
    }

    @Test
    public void findAllTest() throws IOException {
        //5.执行方法
        List<User> users = userDao.findAll();
        for(User u: users){
            System.out.println(u);
        }
    }



    @Test
    public void findOneTest() {
        List<User> users = userDao.findOne("%哥%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findByVoTest() throws IOException {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%哥%");
        queryVo.setUser(user);
        List<User> users = userDao.findByVo(queryVo);
        for(User u: users){
            System.out.println(u);
        }
    }


    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setUsername("阿狗");
        List<User> byCondition = userDao.findByCondition(user);
        System.out.println(byCondition);
    }

    @Test
    public void testFindByQueryVo(){
        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(5);
        list.add(8);
        queryVo.setIds(list);
        List<User> byCondition = userDao.findByQueryVo(queryVo);
        System.out.println(byCondition);
    }
}
