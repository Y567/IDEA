package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

public class JdbcConfig {
    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Bean(name = "runner")  //该注解的作用就是将返回值放入spring容器中key为runner
    @Scope("prototype")
    public QueryRunner getQueryRunner(DataSource ds){
        return new QueryRunner(ds);
    }

    @Bean(name = "ds")
    public DataSource getDataSource(){
        ComboPooledDataSource ds = null;
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(user);
            ds.setPassword(password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ds;
    }
}
