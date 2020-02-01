package com.gyy.dao.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import com.gyy.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


public class AccountDaoImpl implements AccountDao {
    //dbutils里的
    private QueryRunner runner;
    //需要注入一个ConnectionUtils，保证是同一个con在操作数据库
    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account> findAllAccount() {
        try {
            return runner.query(connectionUtil.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account findAccountById(int id) {
        try {
            return runner.query(connectionUtil.getThreadConnection(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int id) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"delete from account where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transfrom(String sourceName, String targetName, float money) {
        //1.查询出要转账的人
        Account sourceAccount = findAccountByName(sourceName);
        //2.查询出给谁转账
        Account targetAccount = findAccountByName(targetName);
        //3.更新两者的账户
        sourceAccount.setMoney(sourceAccount.getMoney()-money);
        targetAccount.setMoney(targetAccount.getMoney()+money);
        //4.更新数据库
        updateAccount(sourceAccount);

//        int i = 1/0;

        updateAccount(targetAccount);
    }

    public Account findAccountByName(String name) {
        try {
            List<Account> accounts = runner.query(connectionUtil.getThreadConnection(),"select * from account where name = ?", new BeanListHandler<Account>(Account.class), name);
            if(accounts == null || accounts.size() == 0){
                return null;
            }
            if(accounts.size() > 1){
                throw new RuntimeException("存在多个名字相同的用户");
            }
            return accounts.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
