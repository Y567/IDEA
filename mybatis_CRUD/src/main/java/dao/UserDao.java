package dao;

import domain.QueryVo;
import domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     */

    List<User> findAll();

    /**
     * 保存用户
     */

    void saveUser(User user);

    /**
     * 更新用户信息
     */
    void updateUser(User user);

    /**
     * 根据Id值删除某个用户
     */

    void deleteUser(int id);

    /**
     * 模糊查询包含哥字的用户
     */

    List<User> findOne(String username);

    /**
     * 使用条件对象来查询
     */

    List<User> findByVo(QueryVo vo);
}
