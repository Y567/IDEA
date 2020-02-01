package com.gyy.service.impl;

import com.gyy.service.AccountService;

public class AccountServiceImpl implements AccountService {
    public void saveAccount() {
        System.out.println("保存账户更新方法执行了");
    }

    public void updateAccount(int id) {
        System.out.println("更新账户方法执行了");
    }

    public int deleteAccount() {
        System.out.println("删除账户方法执行了");
        return 0;
    }
}
