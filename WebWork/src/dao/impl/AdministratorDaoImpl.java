package dao.impl;

import dao.AdministratorDao;
import domain.Administrator;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.DruidUtils;

public class AdministratorDaoImpl implements AdministratorDao {
    private JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
    @Override
    public Administrator findAdministrator(String username,String password) {
        //定义sql语句
        String sql = "select * from Administrator where username = ? and password = ?";
        //查询
        try{
            Administrator administrator = template.queryForObject(sql, new BeanPropertyRowMapper<>(Administrator.class), username, password);
            return administrator;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
