import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.JedisPoolUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    /*
    对String 类型的操作
     */
    @Test
    public void test1(){
        //1.0创建Jedis对象,并指定主机地址和端口号
        //刚刚忘了指定但是和预期效果一样，说明默认绑定了本机以及6379端口号
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //2.0使用
        jedis.set("username","gyy");
        //可以使用一个方法来设置有效时间的key value
        jedis.setex("activecode",20,"5432");

        //3.0释放
        jedis.close();
    }

    /*
对hash 类型的操作
 */
    @Test
    public void test2(){
        //1.0创建Jedis对象,并指定主机地址和端口号
        //刚刚忘了指定但是和预期效果一样，说明默认绑定了本机以及6379端口号
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //2.0使用
        jedis.hset("user","username","阿坤");
        jedis.hset("user","password","akun");
        jedis.hset("user","age","kunkun123456");

        Map<String, String> user = jedis.hgetAll("user");
        Set<String> key = user.keySet();
        for (String s : key) {
            String value = user.get(s);
            System.out.println(s+":"+value);
        }

        //3.0释放
        jedis.close();
    }


    /*
对list 类型的操作
 */
    @Test
    public void test3(){
        //1.0创建Jedis对象,并指定主机地址和端口号
        //刚刚忘了指定但是和预期效果一样，说明默认绑定了本机以及6379端口号
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //2.0使用
        jedis.lpush("myList","a","b","c");//从左边插入
        jedis.rpush("myList","a","b","c");//从右边插入

        //获取全部元素
        List<String> myList = jedis.lrange("myList", 0, -1);
        System.out.println(myList);

        //左边出一个元素
        String left = jedis.lpop("myList");
        System.out.println(left);

        //右边出
        String right = jedis.rpop("myList");
        System.out.println(right);

        //3.0释放
        jedis.close();
    }

    /*
对set 类型的操作
*/
    @Test
    public void test4(){
        //1.0创建Jedis对象,并指定主机地址和端口号
        //刚刚忘了指定但是和预期效果一样，说明默认绑定了本机以及6379端口号
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //2.0使用
        jedis.sadd("mySet","a","b","c");

        //获取值
        Set<String> mySet = jedis.smembers("mySet");

        System.out.println(mySet);

        //3.0释放
        jedis.close();
    }


    /*
对soredSet 类型的操作
*/
    @Test
    public void test5(){
        //1.0创建Jedis对象,并指定主机地址和端口号
        //刚刚忘了指定但是和预期效果一样，说明默认绑定了本机以及6379端口号
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //2.0使用
        jedis.zadd("mySortedSet",1,"阿狗");
        jedis.zadd("mySortedSet",2,"阿狗2");
        jedis.zadd("mySortedSet",5,"阿狗3");

        //获取值
        Set<String> mySortedSet = jedis.zrange("mySortedSet", 0, -1);
        System.out.println(mySortedSet);
        //3.0释放
        jedis.close();
    }


    /*
    Jedis连接池使用
*/
    @Test
    public void test6(){

        //可以创建一个配置对象来对连接池进行配置，也可以使用默认配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(50);
        //设置最大空闲连接
        jedisPoolConfig.setMaxIdle(10);
        //1.0创建连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
        Jedis jedis = jedisPool.getResource();

        //2.0使用
        jedis.set("username","baby");

        //3.0释放(这里的close是将Jedis对象归还给连接池)
        jedis.close();
    }


    /*
    Jedis连接池工具类的使用
*/
    @Test
    public void test7(){

        //调用工具类方法获得连接
        Jedis jedis = JedisPoolUtils.getJedis();

        //2.0使用
        jedis.set("username","baby2");

        //3.0释放(这里的close是将Jedis对象归还给连接池)
        jedis.close();
    }
}
