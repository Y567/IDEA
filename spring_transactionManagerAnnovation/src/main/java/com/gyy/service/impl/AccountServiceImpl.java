package com.gyy.service.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import com.gyy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Account findAccountById(int id) {
        return accountDao.findAccountById(id);
    }

    public void transfrom(String sourceName, String targetName, float money) {
        accountDao.transfrom(sourceName,targetName,money);
    }
}
