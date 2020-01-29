package com.gyy.test;

/**
 * 使用junit单元测试测试配置
 */

import com.gyy.domain.Account;
import com.gyy.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring整合junit的步骤
 * 1. 导入spring-test的坐标
 * 2. 使用junit提供的注解Runwith替换junit的main方法为spring提供的
 * 3. 指定spring创建IOC的方式是xml还是注解
 * @ContextConfiguration:
 *     Locations:xml方式加上classpath表示是类路径下
 *     classes:注解方式
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = config.Config.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountServiceImpl;
    @Test
    public void testFindAllAccount(){
        //3.执行方法
        List<Account> accounts = accountServiceImpl.findAllAccount();
        System.out.println(accounts);
    }

    @Test
    public void testFindAccountById(){
        //3.执行方法
        Account account = accountServiceImpl.findAccountById(3);
        System.out.println(account);
    }

    @Test
    public void testSaveAccount(){
        Account account = new Account();
        account.setName("阿狗");
        account.setMoney(55555f);
        //3.执行方法
        accountServiceImpl.saveAccount(account);
    }

    @Test
    public void testUpdateAccount(){
        Account account = new Account();
        account.setId(4);
        account.setName("阿狗");
        account.setMoney(55445f);
        //3.执行方法
        accountServiceImpl.updateAccount(account);
    }

    @Test
    public void testDeleteAccount(){
       //3.执行方法
        accountServiceImpl.deleteAccount(4);
    }
}
