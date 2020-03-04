package com.gyy.dao.impl;

import com.gyy.dao.ImageDao;
import com.gyy.domain.Image;
import com.gyy.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImageDaoImpl implements ImageDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void insertImage(Image image) {
        //1.定义sql语句
        String sql = "insert into image(uid,imageName,size,uploadTime,contentType,path,md5) values(?,?,?,?,?,?,?)";
        //2.执行语句
        jdbcTemplate.update(sql,image.getUid(),image.getImageName(),image.getSize(),image.getUploadTime(),image.getContentType(),image.getPath(),image.getMd5());
    }

    @Override
    public List<Image> findAll(int uid) {
        //1.定义sql语句
        String sql = "select * from image where uid = ?";
        //2.执行方法
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Image>(Image.class),uid);
    }

    @Override
    public Image findByImageId(int imageId, int uid) {
        //1.定义sql语句
        String sql = "select * from image where uid = ? and imageId = ?";
        //2.执行方法
        Image image = null;
        try {
            image = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Image>(Image.class),uid,imageId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            //数据库中没有数据
            return null;
        }
        return image;
    }

    @Override
    public boolean deleteImageByImageId(int imageId, int uid) {
        //1.定义sql语句
        String sql = "delete from image where uid = ? and imageId = ?";
        //2.执行方法
        jdbcTemplate.update(sql,uid,imageId);
        return true;
    }

    @Override
    public Image findImageByMD5(String md5, int uid) {
        //1.定义sql语句,出现了Bug限制查一条记录。因为可能出现多个如果出现了多个就会抛异常返回null
        String sql = "select * from image where uid = ? and md5 = ? limit 0,1";
        Image image = null;
        //2.执行方法
        try{
            image = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Image>(Image.class),uid,md5);
        }catch (DataAccessException e){
            //数据库中没有数据
            return null;
        }
        return image;
    }

    /**
     * 该方法时用来组成动态sql语句的
     * @param sql    初始化sql
     * @param values ？对应的值,即查询条件
     * @return
     */
    private String dynamicSql(String sql, Map<String, String> condition, List<Object> values){
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map集合看是否用户有查询的数据
        Set<String> keys = condition.keySet();
        for(String key:keys){
            String value = condition.get(key);
            if(value != null && !"".equals(value)){
                //只要有值的时候才将查询条件拼接到sql语句上
                sb.append(" and "+key+" like ?");
                //模糊查询
                values.add("%"+value+"%");
            }
        }
        return sb.toString();
    }

    @Override
    public int findTotalCount(Map<String, String> condition, int uid) {
        //1.定义初始化sql语句
        String sql = "select count(*) from image where uid = ?";

        //2.定义一个集合存储查询条件的值，之后数据库查询使用
        List<Object> values = new ArrayList<Object>();
        values.add(uid);
        String dynamicSql = dynamicSql(sql, condition, values);

//        System.out.println("测试一下:"+dynamicSql+"条件为"+values);

        //3.执行方法,这里传入的是Integer返回的就是Integer
        int result = 0;
        try{
            result = jdbcTemplate.queryForObject(dynamicSql,Integer.class,values.toArray());
        }catch (DataAccessException e){
            //数据库中没有数据
            return 0;
        }
        return result;
    }

    @Override
    public List<Image> findByPage(int currentPage, int rows, Map<String, String> condition, int uid) {
        //1.定义初始化sql语句
        String sql = "select * from image where uid = ?";

        //2.组成动态sql语句
        List<Object> values = new ArrayList<Object>();
        values.add(uid);
        String dynamicSql = dynamicSql(sql, condition, values);

        //2.1这里需要再添加一个limit语句，所以还得拼接
        dynamicSql += " limit ?,?";
        values.add((currentPage-1)*rows);
        values.add(rows);

//        System.out.println("真正的数据sql:"+dynamicSql+"条件为"+values);

        //3.执行方法,注意limit的开始下标需要计算 开始下标=（当前页码数-1）* rows
        return jdbcTemplate.query(dynamicSql, new BeanPropertyRowMapper<Image>(Image.class),values.toArray());
    }
}
