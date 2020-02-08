package com.gyy.dao;

import com.gyy.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 持久层
 */
@Repository
public interface AccountDao {

    /**
     * 查询所有的账户信息
     * @return
     */
    @Select("select * from account")
    List<Account> findAccountAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into account(name,money) values(#{name},#{money})")
    void saveAccount(Account account);
}
