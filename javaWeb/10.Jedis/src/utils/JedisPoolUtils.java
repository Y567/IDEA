package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 提供获取Jedis连接对象的方法
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static{
        //1.0加载配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedisPoolUtils.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.0获取属性值并创建配置对象
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String setMaxTotal = properties.getProperty("setMaxTotal");
        String setMaxIdle = properties.getProperty("setMaxIdle");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置配置
        jedisPoolConfig.setMaxTotal(Integer.parseInt(setMaxTotal));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(setMaxIdle));
        //3.0给连接池对象赋值
        jedisPool = new JedisPool(jedisPoolConfig,host,Integer.parseInt(port));
    }


    //返回连接对象的方法
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
