package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        User byUsername = userDao.findByUsername(user.getUsername());
        if(byUsername != null){
            return false;
        }else{
            userDao.saveUser(user);
            return true;
        }
    }
}
