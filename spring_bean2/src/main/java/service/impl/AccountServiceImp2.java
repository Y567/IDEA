package service.impl;

import service.AccountService;

import java.util.Date;

public class AccountServiceImp2 implements AccountService {
    private String name;
    private int age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
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
