package com.gyy.test;

/**
 * 使用junit单元测试测试配置
 */

import com.gyy.domain.Account;
import com.gyy.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
public class AccountServiceTest {
    @Test
    public void testFindAllAccount(){
        //1.获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取业务层实现类对象
        AccountServiceImpl accountServiceImpl = cpc.getBean("accountServiceImpl", AccountServiceImpl.class);
        //3.执行方法
        List<Account> accounts = accountServiceImpl.findAllAccount();
        System.out.println(accounts);
        cpc.close();
    }

    @Test
    public void testFindAccountById(){
        //1.获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取业务层实现类对象
        AccountServiceImpl accountServiceImpl = cpc.getBean("accountServiceImpl", AccountServiceImpl.class);
        //3.执行方法
        Account account = accountServiceImpl.findAccountById(3);
        System.out.println(account);
        cpc.close();
    }

    @Test
    public void testSaveAccount(){
        Account account = new Account();
        account.setName("阿狗");
        account.setMoney(55555f);
        //1.获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取业务层实现类对象
        AccountServiceImpl accountServiceImpl = cpc.getBean("accountServiceImpl", AccountServiceImpl.class);
        //3.执行方法
        accountServiceImpl.saveAccount(account);
        cpc.close();
    }

    @Test
    public void testUpdateAccount(){
        Account account = new Account();
        account.setId(4);
        account.setName("阿狗");
        account.setMoney(55445f);
        //1.获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取业务层实现类对象
        AccountServiceImpl accountServiceImpl = cpc.getBean("accountServiceImpl", AccountServiceImpl.class);
        //3.执行方法
        accountServiceImpl.updateAccount(account);
        cpc.close();
    }

    @Test
    public void testDeleteAccount(){
        //1.获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取业务层实现类对象
        AccountServiceImpl accountServiceImpl = cpc.getBean("accountServiceImpl", AccountServiceImpl.class);
        //3.执行方法
        accountServiceImpl.deleteAccount(4);
        cpc.close();
    }
}
