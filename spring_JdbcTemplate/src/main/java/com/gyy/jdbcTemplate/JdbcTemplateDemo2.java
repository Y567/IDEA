package com.gyy.jdbcTemplate;

import com.gyy.domain.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        //获取容器
        ClassPathXmlApplicationContext cpc = new ClassPathXmlApplicationContext("bean.xml");
        //获取jdbcTemplate对象
        JdbcTemplate jt = (JdbcTemplate) cpc.getBean("jdbcTemplate");
        //操作方法
        //jt.execute("insert into account(name,money) values('阿超',1000)");

        //保存
        jt.update("insert into account(name,money) values(?,?)","小强",1000f);
        //更新
//        jt.update("update from account set name = ?,money = ? where id = ?","小强",2222,4);
//        //删除
//        jt.update("delete from account where id = ?",4);
//        //查询返回集合
//        List<Account> accounts = jt.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
//        System.out.println(accounts);
//        //查询返回一个
//        Account account = accounts.get(0);
//        System.out.println(account);
//        //查询返回一行一列
//        Integer result = jt.queryForObject("select count(*) from account where id < ?", Integer.class, 7);
//        System.out.println("结果为："+result);

    }
}
