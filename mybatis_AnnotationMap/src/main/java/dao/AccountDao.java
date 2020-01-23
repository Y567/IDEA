package dao;

import domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有账户
     */
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            //@One表示一对一的配置,select一定要具体到方法名称
            @Result(property = "user",column = "uid",one = @One(select = "dao.UserDao.findById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 按照id查询
     */
    @Select("select * from account where uid=#{uid}")
    Account findById();
}
