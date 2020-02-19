package com.gyy.dao;

import com.gyy.domain.User;

public interface UserDao {
    /**
     * 保存用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     * 查询数据库中该邮箱是否已经被注册
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据用户的id更新用户的状态为激活态
     * @param id
     */
    void activeUser(int id);

    /**
     * 登录的时候使用，根据邮箱名和密码查询
     * @param email
     * @param password
     * @return
     */
    User findByEmailAndPassword(String email,String password);
}
