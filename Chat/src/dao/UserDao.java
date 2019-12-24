package dao;

import domain.User;

/**
 * 数据库的抽象层
 */
public interface UserDao {
    public User login(User user);
}
