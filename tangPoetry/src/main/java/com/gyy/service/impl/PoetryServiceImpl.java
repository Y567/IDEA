package com.gyy.service.impl;

import com.gyy.dao.PoetryDao;
import com.gyy.dao.impl.PoetryDaoImpl;
import com.gyy.domain.Echart;
import com.gyy.service.PoetryService;

import java.util.List;
import java.util.Map;

public class PoetryServiceImpl implements PoetryService {

    private PoetryDao poetryDao = new PoetryDaoImpl();

    @Override
    public List<Echart> findByAmount(int left, int right) {
        return poetryDao.findByAmount(left,right);
    }

    public Map<String, Integer> findWordsByName(String name) {
        return poetryDao.findWordsByName(name);
    }

    @Override
    public List<String> findPoetByName(int i) {
        return poetryDao.findPoetByName(i);
    }
}
