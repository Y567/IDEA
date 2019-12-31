package dao;

import domain.Province;

import java.util.List;

public interface ProvinceDao {

    //查询所有省份
    public List<Province> findAll();
}
