package com.gyy.service.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import com.gyy.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public Account findAccountById(int id) {
        return accountDao.findAccountById(id);
    }

    public void transfrom(String sourceName, String targetName, float money) {
        accountDao.transfrom(sourceName,targetName,money);
    }
}
