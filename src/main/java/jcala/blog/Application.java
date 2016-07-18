package jcala.blog;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	@Bean(name = "hikariDataSource")
	public DataSource hikariDataSource() {
		HikariDataSource hds = new HikariDataSource();
		hds.setUsername("root");
		hds.setPassword("10573962Zj");
		hds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jcalaBlog?useUnicode=true&amp;characterEncoding=UTF-8");
		hds.setDriverClassName("com.mysql.jdbc.Driver");
		hds.setConnectionTimeout(30000);
		hds.setMaximumPoolSize(4);
		hds.setMinimumIdle(20);
		return hds;
	}
	@Bean(name = "sqlSessionFactory")
	@Autowired
	@Qualifier("hikariDataSource")
	public SqlSessionFactory sqlSessionFactory(DataSource hikariDataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(hikariDataSource);
		bean.setTransactionFactory(new SpringManagedTransactionFactory());
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.addMappers("jcala.blog.mapping");
		bean.setConfiguration(config);
		return bean.getObject();
	}

	@Bean(name = "sqlSessionTemplate")
	@Autowired
	@Qualifier("sqlSessionFactory")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
