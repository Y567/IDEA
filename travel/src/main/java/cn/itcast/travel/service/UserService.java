package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 业务层的注册方法
     * @param user
     * @return
     */
    boolean regist(User user);
}
