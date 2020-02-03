package com.gyy.dao.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
    }

    public Account findAccountById(int id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), id);
        if(accounts == null || accounts.isEmpty()){
            return null;
        }
        if(accounts.size() > 1){
            throw new RuntimeException("数据异常，存在相同id的账户");
        }
        return accounts.get(0);
    }

    public Account findAccountByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if(accounts == null || accounts.isEmpty()){
            return null;
        }
        if(accounts.size() > 1){
            throw new RuntimeException("数据异常，存在相同名字的账户");
        }
        return accounts.get(0);
    }

    public void transfrom(String sourceName, String targetName, float money) {
        //查出source账户
        Account source = findAccountByName(sourceName);
        //查出target账户
        Account target = findAccountByName(targetName);
        //改变钱
        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);
        //更新账户信息
        updateAccount(source);
        updateAccount(target);
    }
}
