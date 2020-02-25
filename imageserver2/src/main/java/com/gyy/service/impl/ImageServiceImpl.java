package com.gyy.service.impl;

import com.gyy.dao.ImageDao;
import com.gyy.dao.impl.ImageDaoImpl;
import com.gyy.domain.Image;
import com.gyy.domain.PageBean;
import com.gyy.service.ImageService;

import java.util.List;
import java.util.Map;

public class ImageServiceImpl implements ImageService {

    private ImageDao imageDao = new ImageDaoImpl();

    @Override
    public void insertImage(Image image) {
        imageDao.insertImage(image);
    }

    @Override
    public List<Image> findAll(int uid) {
        return imageDao.findAll(uid);
    }

    @Override
    public Image findByImageId(int imageId, int uid) {
        return imageDao.findByImageId(imageId,uid);
    }

    @Override
    public boolean deleteImageByImageId(int imageId, int uid) {
        return imageDao.deleteImageByImageId(imageId,uid);
    }

    @Override
    public Image findImageByMD5(String md5, int uid) {
        return imageDao.findImageByMD5(md5,uid);
    }

    @Override
    public PageBean<Image> findByPage(int currentPage, int rows, Map<String, String> condition, int uid) {
        //1.首先我们需要判断当前页码是否小于等于0
        if(currentPage <= 0){
            currentPage = 1;
        }

        //2.查询数据库中的信息并封装PageBean对象
        PageBean<Image> pb = new PageBean<>();
        //2.1查询总记录数
        int totalCount = imageDao.findTotalCount(condition,uid);
        //2.2查询主要数据
        List<Image> images = imageDao.findByPage(currentPage,rows,condition,uid);
        //2.3计算总页码数
        int totalPage = (totalCount % pb.getRows()) == 0 ? (totalCount / pb.getRows()) : ((totalCount / pb.getRows()) + 1);

        //封装数据并返回
        pb.setCurrentPage(currentPage);
        pb.setData(images);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);

        return pb;
    }
}
