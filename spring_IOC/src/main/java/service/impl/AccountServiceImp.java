package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImp;
import service.AccountService;

public class AccountServiceImp implements AccountService {
    private AccountDao accountDao = new AccountDaoImp();
    //解耦
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
