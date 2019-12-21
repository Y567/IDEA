package service.impl;

import dao.AdministratorDao;
import dao.impl.AdministratorDaoImpl;
import domain.Administrator;
import service.AdminitratorService;

public class AdministratorServiceImpl implements AdminitratorService {

    //管理员业务操作的dao对象
    private AdministratorDao dao = new AdministratorDaoImpl();

    //业务操作
    @Override
    public Administrator findAdministrator(Administrator administrator){
        return dao.findAdministrator(administrator.getUsername(),administrator.getPassword());
    }
}
