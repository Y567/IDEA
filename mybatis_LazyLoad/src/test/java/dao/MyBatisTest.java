package dao;

import domain.Account;
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
    private SqlSession sqlSession;
    private UserDao userDao;
    private AccountDao accountDao;

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
        accountDao = sqlSession.getMapper(AccountDao.class);
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
    public void userFindAll(){
        List<User> users = userDao.findAll();
      /*  for(User user:users){
            System.out.println(user);
            System.out.println("账户信息");
            System.out.println(user.getAccounts());
        }*/
    }


    @Test
    public void accountFindAll() throws IOException {
        //5.执行方法
        List<Account> accounts = accountDao.findAll();
        for (Account u : accounts) {
            System.out.println(u);
            System.out.println("每个账户的所属用户信息");
            System.out.println(u.getUser());
        }
    }
}

