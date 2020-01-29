package com.gyy.dao.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    //dbutils里的
    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account> findAllAccount() {
        try {
            return runner.query("select * from account",new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account findAccountById(int id) {
        try {
            return runner.query("select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account account) {
        try {
            runner.update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int id) {
        try {
            runner.update("delete from account where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
