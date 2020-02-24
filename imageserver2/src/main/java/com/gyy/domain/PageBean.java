package com.gyy.domain;

import java.util.List;

/**
 * 一个PageBean对象就是一个页面展示的内容
 * @param <T>
 */
public class PageBean<T> {

    private int totalCount;     //总记录数
    private int totalPage;      //总页码数
    private int currentPage;    //当前页码数
    private List<T> data;       //封装的数据
    private int rows = 15;           //每页展示的图片数目

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", data=" + data +
                ", rows=" + rows +
                '}';
    }
}
