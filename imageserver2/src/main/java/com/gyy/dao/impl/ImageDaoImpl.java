package com.gyy.dao.impl;

import com.gyy.dao.ImageDao;
import com.gyy.domain.Image;
import com.gyy.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
        //1.定义sql语句
        String sql = "select * from image where md5 = ? and uid = ?";
        Image image = null;
        //2.执行方法
        try{
            image = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Image>(Image.class),md5,uid);
        }catch (DataAccessException e){
            //数据库中没有数据
            return null;
        }
        return image;
    }
}
