package domain;

import java.util.List;

/*
分页对象（封装了分页的全部信息）
 */
public class PageBean<T> {
    private int totalCount;  //总记录数
    private int currentPage;  //当前页码
    private int rows;       //每页展示的行数
    private int totalPage;  //总页码数
    private List<T> list;   //展示的用户信息封装在列表里

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
