package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImp;
import factory.BeanFactory;
import service.AccountService;

public class AccountServiceImp implements AccountService {
//    private AccountDao accountDao = new AccountDaoImp();
    //解耦
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("AccountDao");
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
