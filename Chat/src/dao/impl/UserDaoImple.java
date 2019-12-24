package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.DruidUtils;

public class UserDaoImple implements UserDao {
    //利用JDBCTemplate实现数据库操作
    private JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
    /**
     * 从数据库中查询用户
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        //1.定义sql语句
        String sql = "select * from user where username = ? and password = ?";
        //2.执行语句
        User result;
        try{
            //这里查不到的话会出现一个异常，但是我们希望返回的是null，所以需要抓住异常
            result = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
        }catch(Exception e){
            return null;
        }
        return result;

    }
}
