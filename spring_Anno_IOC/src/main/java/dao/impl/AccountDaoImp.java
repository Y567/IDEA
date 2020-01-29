package dao.impl;

import dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImp implements AccountDao {
    public void saveAccount() {
        System.out.println("111111111");
    }
}
