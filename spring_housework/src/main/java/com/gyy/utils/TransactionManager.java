package com.gyy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 提供事务管理的功能
 */

@Component("tx")
public class TransactionManager {

    @Autowired
    private ConnectionUtil connectionUtil;


    //开启事务
    public void begin(){
        try{
            //关闭自动提交，改为手动提交
            connectionUtil.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit(){
        try{
            //关闭自动提交，改为手动提交
            connectionUtil.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollback(){
        try{
            //回滚
            connectionUtil.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //关闭事务
    public void close(){
        try{
            //归还连接对象回连接池
            connectionUtil.getThreadConnection().close();
            //需要将与线程绑定的连接解绑，因为与线程绑定的连接归还给了连接池，导致其不能使用，需要解绑
            connectionUtil.remove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
