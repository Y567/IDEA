package dao.impl;

import dao.UserDao;
import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;
    /**
     * 自己写dao实现类
     * @return
     */

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    public List<User> findAll() {
        //1.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //2.执行selectList方法获取数据信息
        List<User> users = sqlSession.selectList("dao.UserDao.findAll");
        //3.释放资源
        sqlSession.close();
        return users;
    }
}
