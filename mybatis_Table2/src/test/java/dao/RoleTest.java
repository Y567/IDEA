package dao;

import domain.Role;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {
    private InputStream in;
    private SqlSession sqlSession;
    private RoleDao roleDao;

    @Before  //表示在测试方法之前执行
    public void init() throws IOException {
        //1.加载配置文件获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取代理对象
        roleDao = sqlSession.getMapper(RoleDao.class);
    }

    @After
    public void release() throws IOException {

        //事务提交
        sqlSession.commit();
        //6.释放资源
        if(sqlSession != null){
            sqlSession.close();
        }
        if(in != null){
            in.close();
        }
    }

    @Test
    public void roleFindAll() throws IOException {
        //5.执行方法
        List<Role> roles = roleDao.findAll();
        for(Role role: roles){
            System.out.println(role);
            System.out.println("角色被赋予了哪些人");
            System.out.println(role.getUsers());
        }
    }



}
