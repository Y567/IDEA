package com.gyy.test;

import com.gyy.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void testFindAccountAll(){
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        AccountService as = (AccountService) ac.getBean("accountService");
        //3.执行测试方法
        as.findAccountAll();
    }
}
