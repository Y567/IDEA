package com.gyy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 保证一个线程绑定一个连接，使得转账要么成功，要么失败
 */
@Component("connectionUtil")
public class ConnectionUtil {

    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    public Connection getThreadConnection(){
        Connection con = null;
        try{
            //1.0先从ThreadLocal中获取
            con = t1.get();
            if(con == null){
                //2.没有的话，从数据源中获取并与线程绑定
                con = dataSource.getConnection();
                t1.set(con);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //3.返回连接对象
        return con;
    }

    //解绑
    public void remove(){
        t1.remove();
    }
}
