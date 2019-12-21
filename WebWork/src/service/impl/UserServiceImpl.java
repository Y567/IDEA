package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.PageBean;
import domain.User;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    //一个dao业务操作类对象（实现了玩家信息的查询）
    private UserDao dao = new UserDaoImpl();
    //调用Dao层的方法返回用户信息
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }

    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public PageBean<User> findUserByPage(int currentPage, int rows, Map<String, String> condition) {
        //1.创建一个PageBean对象
        PageBean<User> pageBean = new PageBean<User>();


        //2.求出总记录数
        int totalCount = dao.findTotalCount(condition);

        //3.求出总页码数
        int totalPage = totalCount%rows==0?totalCount/rows:totalCount/rows+1;
        if(currentPage>=totalPage){
            currentPage=totalPage;
        }

        //健壮性判断
        if(currentPage<=0){
            currentPage=1;
        }

        //4.求出开始索引并查询List集合(集合里存放这要展示的用户信息)
        int start = (currentPage-1)*rows;
        List<User> list = dao.findUserByPageList(start,rows,condition);

        //set值
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(list);
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);

        //返回pageBean对象
        return pageBean;

    }

    public void delUsers(String[] ids) {
        //遍历数组
        for (String id : ids) {
            //这里只需要遍历删除就行
            dao.deleteUserById(Integer.parseInt(id));
        }
    }
}
