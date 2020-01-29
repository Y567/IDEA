package dao.impl;

import dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class AccountDaoImp1 implements AccountDao {
    public void saveAccount() {
        System.out.println("222222222");
    }
}
