package com.gyy.factory;

import com.gyy.service.AccountService;
import com.gyy.service.impl.AccountServiceImpl;
import com.gyy.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 用于产生有事务控制的AccountService
 */
public class BeanFactory {

    private AccountService accountService;

    private TransactionManager tx;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTx(TransactionManager tx) {
        this.tx = tx;
    }

    public AccountService getAccountService() {
        accountService = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                try{
                    //开启事务
                    tx.begin();
                    //执行操作
                    returnValue = method.invoke(accountService, args);
                    //提交事务
                    tx.commit();
                    //返回结果
                    return returnValue;
                }catch (Exception e){
                    //回滚事务
                    tx.rollback();
                    throw new RuntimeException("发生了异常了");
                }finally {
                    //释放资源（关闭事务）
                    tx.close();
                }
            }
        });
        return accountService;
    }


}
