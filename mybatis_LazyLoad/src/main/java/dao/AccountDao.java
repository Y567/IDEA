package dao;

import domain.Account;

import java.util.List;

public interface AccountDao {

    /**
     * 查询所有账户信息
     */

    List<Account> findAll();

    /**
     * 根据id查询账户信息
     */

    Account findById(int id);
}
