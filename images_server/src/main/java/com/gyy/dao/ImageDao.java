package com.gyy.dao;

import com.gyy.domain.Image;

import java.util.List;

public interface ImageDao {
    /**
     * 插入图片到数据库
     * @param image
     */
    void insert(Image image);

    /**
     * 查询所有的图片
     * @return
     */
    List<Image> findAll();

    /**
     * 通过imageId查询指定的Id
     * @param imageId
     * @return
     */
    Image findById(int imageId);

    /**
     * 通过imageId删除指定图片
     * @param imageId
     */
    void delete(int imageId);

}
