package com.gyy.test;

import com.gyy.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        //1.获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        AccountService as = (AccountService) cpc.getBean("accountService");
        //3.执行方法
        as.saveAccount();
        cpc.close();
    }
}
