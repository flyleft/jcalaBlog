package me.jcala.blog.conf;

import com.zaxxer.hikari.HikariDataSource;
import me.jcala.blog.mapping.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MybatisConf{
    @Value("${spring.dataSource.driverClassName}")
    private String driver;
    @Value("${spring.dataSource.url}")
    private String url;
    @Value("${spring.dataSource.username}")
    private String username;
    @Value("${spring.dataSource.password}")
    private String password;

    public DataSource hikariDataSource() {
        HikariDataSource hds = new HikariDataSource();
        hds.setUsername(username);
        hds.setPassword(password);
        hds.setJdbcUrl(url);
        hds.setDriverClassName(driver);
        hds.setConnectionTimeout(30000);
        hds.setMaximumPoolSize(50);
        hds.setMinimumIdle(5);
        return hds;
    }
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(hikariDataSource());
        bean.setTransactionFactory(new SpringManagedTransactionFactory());
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.addMappers("me.jcala.blog.mapping");
        bean.setConfiguration(config);
        return bean.getObject();
    }
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
        SqlSessionFactory sqlSessionFactory=sqlSessionFactory();
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean(name = "userMapper")
    @Autowired
    public UserMapper userMapper(SqlSessionTemplate sqlSessionTemplate) {
        return sqlSessionTemplate.getMapper(UserMapper.class);
    }
}
