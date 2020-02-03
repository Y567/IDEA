package com.gyy.dao;

import com.gyy.domain.Account;

public interface AccountDao {

    /**
     * 更新账户信息
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据Id查询
     * @param id 参数
     * @return   返回账户信息
     */
    Account findAccountById(int id);

    /**
     * 根据姓名查询
     * @param name 名字
     * @return
     */
    Account findAccountByName(String name);

    /**
     * 转账
     * @param sourceName 出钱人
     * @param targetName 收钱人
     * @param money      交易金额
     */
    void transfrom(String sourceName,String targetName,float money);
}
