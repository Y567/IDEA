package dao;

import domain.Administrator;

import java.util.List;

public interface AdministratorDao {
    /**
     * 管理员数据操作
     * @return
     */
    public Administrator findAdministrator(String username,String password);
}
