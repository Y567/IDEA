package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.DruidUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
    //利用JDBCTemplate实现数据库操作
    @Override
    public List<User> findAll() {
        //1.定义sql语句
        String sql = "select * from user";
        //2.执行语句
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public void addUser(User user) {
        //定义sql语句
        String sql = "insert into user values(null,?,?,?,?,?)";
        //执行sql并返回
        template.update(sql,user.getUsername(),user.getPassword(),user.getTell(),user.getGender(),user.getBirthday());
    }

    @Override
    public void deleteUserById(int id) {
        //定义SQL语句
        String sql = "delete from user where id = ?";
        //执行语句
        template.update(sql,id);
    }

    @Override
    public User findUserById(int id) {
        //定义sql语句
        String sql = "select * from user where id=?";
        //执行
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void updateUser(User user) {
        //定义sql语句
        String sql = "update user set username=?,password=?,tell=?,gender=?,birthday=? where id=?";
        //执行语句
        template.update(sql,user.getUsername(),user.getPassword(),user.getTell(),user.getGender(),user.getBirthday(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String> condition) {
        String sql = "select count(*) from user where 1=1";
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer(sql);
        Set<String> keys = condition.keySet();
        for (String key : keys) {
            String value = condition.get(key);
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findUserByPageList(int start, int rows, Map<String, String> condition) {
        String sql = "select * from user where 1=1";
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer(sql);
        Set<String> keys = condition.keySet();
        for (String key : keys) {
            String value = condition.get(key);
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");

        params.add(start);
        params.add(rows);
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
