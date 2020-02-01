package com.gyy.service.impl;

import com.gyy.dao.AccountDao;
import com.gyy.domain.Account;
import com.gyy.service.AccountService;
import com.gyy.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl_OLD implements AccountService {

    private AccountDao accountDao;
    private TransactionManager tx;

    public void setTx(TransactionManager tx) {
        this.tx = tx;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try{
            //开启事务
            tx.begin();
            //执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //提交事务
            tx.commit();
            //返回结果
            return accounts;
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }

    public Account findAccountById(int id) {
        try{
            //开启事务
            tx.begin();
            //执行操作
            Account account = accountDao.findAccountById(id);
            //提交事务
            tx.commit();
            //返回结果
            return account;
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }

    public void updateAccount(Account account) {
        try{
            //开启事务
            tx.begin();
            //执行操作
            accountDao.updateAccount(account);
            //提交事务
            tx.commit();
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }

    public void saveAccount(Account account) {
        try{
            //开启事务
            tx.begin();
            //执行操作
            accountDao.saveAccount(account);
            //提交事务
            tx.commit();
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }

    public void deleteAccount(int id) {
        try{
            //开启事务
            tx.begin();
            //执行操作
            accountDao.deleteAccount(id);
            //提交事务
            tx.commit();
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }

    public void transfrom(String sourceName, String targetName, float money) {
        try{
            //开启事务
            tx.begin();
            //执行操作
            accountDao.transfrom(sourceName,targetName,money);
            //提交事务
            tx.commit();
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }

    public Account findAccountByName(String name) {
        try{
            //开启事务
            tx.begin();
            //执行操作
            Account account = accountDao.findAccountByName(name);
            //提交事务
            tx.commit();
            //返回结果
            return account;
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            throw new RuntimeException("发生了异常了");
        }finally {
            //释放资源（关闭事务）
            tx.close();
        }
    }
}
