package com.gyy.service;

import com.gyy.domain.Account;

import java.util.List;

/**
 * 业务层的接口
 */
public interface AccountService {
    /**
     * 查询所有账户
     */
    List<Account> findAllAccount();

    /**
     * 查询一个账户
     */
    Account findAccountById(int id);

    /**
     * 更新一个账户的信息
     */
    void updateAccount(Account account);

    /**
     * 插入一个账户信息
     */
    void saveAccount(Account account);

    /**
     * 删除一个账户的信息
     */
    void deleteAccount(int id);

    /**
     * 转账
     */
    void transfrom(String sourceName, String targetName, float money);

    /**
     * 根据名字查询
     */
    Account findAccountByName(String name);

}
