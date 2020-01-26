package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    /**
     * 这是一个创建AccountDao对象和AccountService的实现类对象的工厂，从而达到解耦的目的
     *
     * 我们需要一个配置文件其内容为实现类的全限定类名用来唯一表示实现类
     * 读取其内容后靠反射来创建对象
     */

    private static Properties properties;
    private static Map<String,Object> map;

    static{
        //静态代码块初始化数据
        properties = new Properties();
        map = new HashMap<String, Object>();
        InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(in);
            //利用map存放实例对象，实现单例，即实例对象不用重复创建
            Enumeration keys = properties.keys();
            while(keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String beanPath = (String) properties.get(key);
                Object o = null;
                try {
                    o = Class.forName(beanPath).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                map.put(key,o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



/*    //获取实现类的方法，返回值需要设置成Object对象
    public static Object getBean(String beanName){
        //获取到全限定类名
        String beanPath = properties.getProperty(beanName);
        //通过反射来创建对象
        Object o = null;
        try {
            o = Class.forName(beanPath).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }*/


    //直接勇map获取的是单例的
    public static Object getBean(String beanName){
        return map.get(beanName);
    }

}
