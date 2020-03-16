package com.gyy.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtil {
    //Jedis连接池
    private static JedisPool jedisPool;

    static{
        //1.加载配置文件
        InputStream in = JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        //2.创建properties文件
        Properties p = new Properties();
        //3.传入参数
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.配置连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(p.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(p.getProperty("maxIdle")));
        //5.创建连接池
        jedisPool = new JedisPool(config, p.getProperty("host"), Integer.parseInt(p.getProperty("port")));
    }

    //获取连接的方法
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    //释放资源
    public static void close(Jedis jedis){
        jedis.close();
    }
}
