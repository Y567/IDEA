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
import java.util.Date;
import java.util.List;

public class AccountTest {
    private SqlSession sqlSession;
    private InputStream in;
    private AccountDao accountDao;

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
        accountDao = sqlSession.getMapper(AccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("每个账户的信息");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

}
