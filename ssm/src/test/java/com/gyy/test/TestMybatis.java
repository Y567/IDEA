package com.gyy.test;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void testFindAccountAll() throws IOException {
        //1.加载配置文件
        InputStream in = Resources.getResourceAsStream("sqlConfigMap.xml");
        //2.创建sqlSessionFactory
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
        //3.创建sqlSession
        SqlSession sqlSession = build.openSession();
        //4.获取代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        //5.执行操作
        List<Account> accounts = accountDao.findAccountAll();
        //6.遍历结果
        for(Account account:accounts){
            System.out.println(account);
        }
        //7.释放资源
        sqlSession.close();
        in.close();
    }


    /**
     * 这里测试过了mybatis，但是整合之后不需要sqlConfigMap.xml文件了，所以删除了
     * @throws IOException
     */
    @Test
    public void testSaveAccount() throws IOException {
        Account account = new Account();
        account.setName("阿狗3");
        account.setMoney(3000d);
        //1.加载配置文件
        InputStream in = Resources.getResourceAsStream("sqlConfigMap.xml");
        //2.创建sqlSessionFactory
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
        //3.创建sqlSession
        SqlSession sqlSession = build.openSession();
        //4.获取代理对象
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        //5.执行操作
        accountDao.saveAccount(account);
        sqlSession.commit();
        //7.释放资源
        sqlSession.close();
        in.close();
    }
}
