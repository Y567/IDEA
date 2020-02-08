package com.gyy.service.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import com.gyy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")  //将该类交给spring容器管理
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAccountAll() {
        System.out.println("假装查询");
        return accountDao.findAccountAll();
    }

    @Override
    public void saveAccount(Account account) {
//        System.out.println("假装保存");
        accountDao.saveAccount(account);
        int i = 10 / 0;
        return;
    }
}
