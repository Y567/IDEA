package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImple;
import domain.User;
import service.UserService;

/**
 * 服务层
 */
public class UserServiceImpl implements UserService {
    //验证登录
    @Override
    public User login(User user) {
        UserDao dao = new UserDaoImple();
        return dao.login(user);
    }
}
