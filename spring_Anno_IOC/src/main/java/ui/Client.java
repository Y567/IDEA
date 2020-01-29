package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;
import service.impl.AccountServiceImp;

public class Client {
    /**
     * 模拟一个servlet调用业务层
     */
    public static void main(String[] args) {
        //1.获取spring的核心容器
        ClassPathXmlApplicationContext cpa = new ClassPathXmlApplicationContext("bean.xml");
        //2.通过id来获取具体的对象
        AccountService as = cpa.getBean("accountServiceImp", AccountServiceImp.class);
        System.out.println(as);
    }
}
