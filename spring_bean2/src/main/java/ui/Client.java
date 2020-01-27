package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;
import service.impl.AccountServiceImp3;

public class Client {
    /**
     * 这里只调用到了service层，dao层被删除了更简洁
     */
    public static void main(String[] args) {
        //1.获取spring的核心容器
        ApplicationContext cpa = new ClassPathXmlApplicationContext("bean.xml");
        //2.通过id来获取具体的对a象
//        AccountService as = (AccountServiceImp) cpa.getBean("AccountService");
        AccountService as = (AccountServiceImp3) cpa.getBean("AccountService");
        as.saveAccount();
    }
}
