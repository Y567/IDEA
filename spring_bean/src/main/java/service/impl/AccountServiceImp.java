package service.impl;

import service.AccountService;

public class AccountServiceImp implements AccountService {
    //解耦
    public void saveAccount() {
        System.out.println("service层的方法调用了");
    }

    //初始化方法
    public void init() {
        System.out.println("对象初始化了");
    }

    //销毁方法
    public void destroy() {
        System.out.println("对象销毁了");
    }


}
