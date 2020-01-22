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
     * 动态查询
     */

    List<User> findByCondition(User user);


    /**
     * 模糊查询
     */
    List<User> findOne(String username);

    /**
     * 使用条件对象来查询
     */

    List<User> findByVo(QueryVo vo);

    /**
     * 使用条件对象提供的ids来查询指定的用户
     */

    List<User> findByQueryVo(QueryVo queryVo);
}
