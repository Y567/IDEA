package service;

import dao.AdministratorDao;
import dao.impl.AdministratorDaoImpl;
import domain.Administrator;

public interface AdminitratorService {
    /*
    管理员查询
     */
    public Administrator findAdministrator(Administrator administrator);
}
