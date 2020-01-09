package dao;

import domain.User;

import java.util.List;

public interface UserDao {

    //实现一个查询所有用户的操作
    public List<User> findAll();
}
