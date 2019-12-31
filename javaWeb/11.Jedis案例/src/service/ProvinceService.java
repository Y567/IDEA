package service;

import domain.Province;

import java.util.List;

public interface ProvinceService {

    public List<Province> findAll();

    //redis缓存提高效率
    public String findAllByRedis();
}
