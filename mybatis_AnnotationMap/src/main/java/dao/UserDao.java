package dao;

import domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

//开启二级缓存
@CacheNamespace(blocking = true)
public interface UserDao {
    /**
     * 解决实体类与数据库表名字不匹配的情况
     */

    /**
     * 查询所有用户
     */

    @Results(id = "userMap",value = {
            @Result(id = true,property = "uid",column = "id"),
            @Result(property = "uname",column = "username"),
            @Result(property = "ubirthday",column = "birthday"),
            @Result(property = "usex",column = "sex"),
            @Result(property = "uaddress",column = "address"),
            @Result(property = "accounts",column = "id",many = @Many(select = "dao.AccountDao.findById",fetchType = FetchType.LAZY))
    })
    @Select("select * from user")
    List<User> findAll();


    /**
     * 根据id查询
     */
    @ResultMap(value = {"userMap"})
    @Select("select * from user where id=#{id}")
    User findById(int id);
}
