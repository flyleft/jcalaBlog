package me.jcala.blog.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
/**
 * Created by jcala on 2016/7/29
 */
@Configuration
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的<tx:annotation-driven/>
public class DataSourceConf {
    @Value("${hikaricp.driver}")
    private String driver;
    @Value("${hikaricp.url}")
    private String url;
    @Value("${hikaricp.username}")
    private String username;
    @Value("${hikaricp.password}")
    private String password;
    @Value("${hikaricp.connectionTimeout}")
    private long connectionTimeout;
    @Value("${hikaricp.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${hikaricp.minimumIdle}")
    private int minimumIdle;

    @Bean(destroyMethod = "close")
    public DataSource hikariDataSource() {
        HikariDataSource hds = new HikariDataSource();
        hds.setUsername(username);
        hds.setPassword(password);
        hds.setJdbcUrl(url);
        hds.setDriverClassName(driver);
        hds.setConnectionTimeout(connectionTimeout);
        hds.setMaximumPoolSize(maximumPoolSize);
        hds.setMinimumIdle(minimumIdle);
        hds.setAutoCommit(false);
        return hds;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager =
                new DataSourceTransactionManager();
        transactionManager.setDataSource(hikariDataSource());
        return transactionManager;
    }
}