package com.gyy.service;

import com.gyy.domain.Account;

import java.util.List;

/**
 * 业务层
 */
public interface AccountService {
    /**
     * 查询所有的账户信息
     * @return
     */
    List<Account> findAccountAll();

    /**
     * 保存账户信息
     * @param account
     */
    void saveAccount(Account account);
}
