package com.gyy.service.impl;

import com.gyy.dao.ImageDao;
import com.gyy.dao.impl.ImageDaoImpl;
import com.gyy.domain.Image;
import com.gyy.service.ImageService;

import java.util.List;

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
}
