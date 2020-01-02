
import dao.impl.UserDaoImpl;
import domain.User;
import org.junit.Test;

import java.util.List;

public class DBTest {
    @Test
    public void test1(){
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> user = userDao.findAll();
        System.out.println(user);
    }
}
