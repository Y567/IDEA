package dao;

import domain.Role;

import java.util.List;

public interface RoleDao {

    /**
     * 查询所有角色
     */
    List<Role> findAll();
}
