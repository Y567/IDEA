package ui;

import factory.BeanFactory;
import service.AccountService;
import service.impl.AccountServiceImp;

public class Client {
    /**
     * 模拟一个servlet调用业务层
     */
    public static void main(String[] args) {
//        AccountServiceImp accountServiceImp = new AccountServiceImp();
        //解耦版本
        //AccountServiceImp accountServiceImp = (AccountServiceImp) BeanFactory.getBean("AccountService");
        for(int i = 0;i < 3;i++){
            AccountServiceImp accountServiceImp = (AccountServiceImp) BeanFactory.getBean("AccountService");
            System.out.println(accountServiceImp);
        }
        //accountServiceImp.saveAccount();
    }
}
