package com.gyy.service;

import com.gyy.domain.Image;
import com.gyy.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface ImageService {
    /**
     * 插入图片到数据库
     * @param image
     */
    void insertImage(Image image);

    /**
     * 查询所有的图片
     * @param uid 当前登录的用户
     * @return
     */
    List<Image> findAll(int uid);

    /**
     * 根据id查询图片
     * @param imageId
     * @param uid
     * @return
     */
    Image findByImageId(int imageId,int uid);

    /**
     * 根据imageId删除图片
     * @param imageId
     * @param uid
     */
    boolean deleteImageByImageId(int imageId,int uid);

    /**
     * 根据md5判断数据库中是否有了上传的图片
     * @param md5
     * @param uid
     * @return
     */
    Image findImageByMD5(String md5,int uid);

    /**
     * 按页查询出数据库中的信息
     * @param currentPage
     * @param rows
     * @param condition
     * @param uid
     * @return
     */
    PageBean<Image> findByPage(int currentPage, int rows, Map<String, String> condition, int uid);
}
