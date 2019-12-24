package service;

import domain.User;

/**
 * 服务的抽象层
 */
public interface UserService {
    public User login(User user);
}
