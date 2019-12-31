package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProvinceDao;
import dao.impl.ProvinceDaoImpl;
import domain.Province;
import redis.clients.jedis.Jedis;
import service.ProvinceService;
import jedis.utils.JedisPoolUtils;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {

    //需要声名一个Dao对象
    private ProvinceDao provinceDao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public String findAllByRedis() {
        System.out.println("aocwenvewivuwevbuwiebv");
        //1.0获取Jedis连接
        Jedis jedis = JedisPoolUtils.getJedis();
        System.out.println("出来了");
        System.out.println(jedis);
        //2.0从Redis对象中获取数据
        String province_json = jedis.get("province");
        //3.0判断redis缓存中是否有数据
        if(province_json==null||province_json.length()==0){

            System.out.println("redis缓存中无数据");
            //没有数据的话需要从数据库中查询
            List<Province> list = provinceDao.findAll();
            //将list对象转换为JSON对象
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                province_json = objectMapper.writeValueAsString(list);
                jedis.set("province",province_json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }else{
            //有数据
            System.out.println("redis缓存中有数据据");
        }
        jedis.close();
        return province_json;  //说明有数据
    }
}
