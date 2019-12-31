package dao.impl;

import dao.ProvinceDao;
import domain.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import jedis.utils.JDBCUtils;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    //需要声名一个JdbcTemplate对象,传入一个数据库连接池
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        //1.0定义sql语句
        String sql = "select * from province";

        //2.0执行sql语句，返回一个List列表
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }
}
