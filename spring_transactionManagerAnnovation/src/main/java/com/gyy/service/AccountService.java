package com.gyy.service;

import com.gyy.domain.Account;

/**
 * 业务层接口
 */
public interface AccountService {
    /**
     * 更新账户信息
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据ID查
     * @param id
     * @return
     */
    Account findAccountById(int id);

    /**
     * 转账
     * @param sourceName  出钱人
     * @param targetName  收钱人
     * @param money       交易金额
     */
    void transfrom(String sourceName,String targetName,float money);
}
