package com.gyy.dao.impl;

import com.gyy.common.JavaImageServerException;
import com.gyy.dao.ImageDao;
import com.gyy.domain.Image;
import com.gyy.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl implements ImageDao {

    @Override
    public void insert(Image image) {
        //1.获取连接
        Connection conn = DBUtil.getConnection();
        //2.定义sql语句
        String sql = "insert into image(imageName,size,uploadTime,contentType,path,md5) values(?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            //3.执行语句
            stmt.setString(1,image.getImageName());
            stmt.setInt(2,image.getSize());
            stmt.setString(3,image.getUploadTime());
            stmt.setString(4,image.getContentType());
            stmt.setString(5,image.getPath());
            stmt.setString(6,image.getMd5());
            int i = stmt.executeUpdate();
            if(i != 1){
                //插入数据库发生异常
                throw new JavaImageServerException("插入数据库出错，影响行数"+i);
            }
        } catch (SQLException | JavaImageServerException e) {
            e.printStackTrace();
        }finally {
            //4.关闭连接
            DBUtil.close(conn,stmt,null);
        }
    }

    @Override
    public List<Image> findAll() {
        List<Image> images = new ArrayList<Image>();
        //1.获取数据库连接
        Connection conn = DBUtil.getConnection();
        //2.定义sql语句
        String sql = "select * from image";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            //3.执行语句
            rs = stmt.executeQuery();
            //4.处理结果集
            while(rs.next()){
                Image image = new Image();
                image.setImageId(rs.getInt("imageId"));
                image.setImageName(rs.getString("imageName"));
                image.setSize(rs.getInt("size"));
                image.setUploadTime(rs.getString("uploadTime"));
                image.setContentType(rs.getString("contentType"));
                image.setPath(rs.getString("path"));
                image.setMd5(rs.getString("md5"));
                images.add(image);
            }
            return images;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.关闭连接
            DBUtil.close(conn,stmt,rs);
        }
        return null;
    }

    @Override
    public Image findById(int imageId) {
        //1.获取数据库连接
        Connection conn = DBUtil.getConnection();
        //2.定义sql语句
        String sql = "select * from image where imageId = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,imageId);
            //3.执行语句
            rs = stmt.executeQuery();
            Image image = new Image();
            //4.处理数据集,id是自增长，所以要么有一条，要么没有数据
            if(rs.next()){
                image.setImageId(rs.getInt("imageId"));
                image.setImageName(rs.getString("imageName"));
                image.setSize(rs.getInt("size"));
                image.setUploadTime(rs.getString("uploadTime"));
                image.setContentType(rs.getString("contentType"));
                image.setPath(rs.getString("path"));
                image.setMd5(rs.getString("md5"));
                return image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5.关闭连接
            DBUtil.close(conn,stmt,rs);
        }
        return null;
    }

    @Override
    public void delete(int imageId) {
        //1.获取连接
        Connection conn = DBUtil.getConnection();
        //2.定义sql语句
        String sql = "delete from image where imageId = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,imageId);
            //3.执行语句
            int i = stmt.executeUpdate();
            if(i != 1){
                throw new JavaImageServerException("删除失败！影响行数："+i);
            }
        } catch (SQLException | JavaImageServerException e) {
            e.printStackTrace();
        }finally {
            //4.关闭连接
            DBUtil.close(conn,stmt,rs);
        }
    }


    public static void main(String[] args) {
        Image image = new Image();
        image.setImageName("阿狗.png");
        image.setUploadTime("20200222");
        //1.测试插入方法
    }
}
