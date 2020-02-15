package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户查询用户名（注册的时候需要用到，防止用户名重复）
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 保存User信息
     * @param user
     */
    void saveUser(User user);
}
