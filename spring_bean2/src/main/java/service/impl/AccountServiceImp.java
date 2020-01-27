package service.impl;

import service.AccountService;

import java.util.Date;

public class AccountServiceImp implements AccountService {
    private String name;
    private int age;
    private Date birthday;

    public AccountServiceImp(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    //解耦
    public void saveAccount() {
        System.out.println("service层的方法调用了");
        System.out.println(name);
        System.out.println(age);
        System.out.println(birthday);
    }


}
