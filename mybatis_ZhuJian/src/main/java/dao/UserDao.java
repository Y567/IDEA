package dao;

import domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    /**
     * mybatis中支持的CRUD的四种注解
     * @Select @Update @Insert @Delete
     */
    /**
     * 查询所有用户
     */

    @Select("select * from user")
    List<User> findAll();

    /**
     * 插入新用户
     */
    @Insert("insert into user values(#{id},#{username},#{birthday},#{sex},#{address})")
    void insertUser(User user);

    /**
     * 更新用户
     */
    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     */

    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);

    /**
     * 根据id查询
     */
    @Select("select * from user where id=#{id}")
    User findById(int id);
}
