package com.gyy.service;

/**
 * 模拟业务层
 */
public interface AccountService {
    /**
     * 模拟的保存账户
     */
    void saveAccount();

    /**
     * 更新账户
     */
    void updateAccount(int id);

    /**
     * 删除账户
     */
    int deleteAccount();
}
