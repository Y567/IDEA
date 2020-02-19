package com.gyy.dao.impl;

import com.gyy.dao.UserDao;
import com.gyy.domain.User;
import com.gyy.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    //spring操作数据库的内置对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void saveUser(User user) {
        //1.定义sql语句
        String sql = "insert into user values(null,?,?,?,?)";
        //2.执行方法
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getStatus());
    }

    @Override
    public User findByEmail(String email) {
        //创建一个实体类
        User user = null;
        //1.定义sql语句
        String sql = "select * from user where email = ?";
        //2.查询数据
        try {
             user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),email);
        } catch (DataAccessException e) {
            //说明没有数据
            return null;
        }
        return user;
    }

    @Override
    public void activeUser(int id) {
        //1.定义sql语句
        String sql = "update user set status = ? where id = ?";
        //2.执行方法
        jdbcTemplate.update(sql,"Y",id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = null;
        //1.定义sql语句
        String sql = "select * from user where email = ? and password = ?";
        //2.执行方法
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),email,password);
        } catch (DataAccessException e) {
            //说明没有数据
            return null;
        }
        return user;
    }
}
