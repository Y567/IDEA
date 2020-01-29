package config;

import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("com.gyy")  //指明创建容器时要扫描的包
@Import(JdbcConfig.class)  //导入其他配置类
@PropertySource("classpath:jdbcConfig.properties")  //用来指定配置文件的位置
public class Config {

}
