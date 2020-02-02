package com.gyy.jdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //创建数据源对象
        DriverManagerDataSource ds = new DriverManagerDataSource();
        //设置信息
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring");
        ds.setUsername("root");
        ds.setPassword("yy5201314");
        //创建jdbcTemplate对象并传入数据源
        JdbcTemplate jt = new JdbcTemplate(ds);
        //操作方法
        jt.execute("insert into account(name,money) values('阿超',1000)");
    }
}
