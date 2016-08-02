package me.jcala.blog.conf;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/8/2.
 */
@Configuration
public class MybatisConf {
    @Bean(name = "mybatisConfiguration")
    public org.apache.ibatis.session.Configuration configuration(){
        val configuration=new org.apache.ibatis.session.Configuration();
        configuration.setLazyLoadingEnabled(true);//设置为延迟加载
        configuration.setAggressiveLazyLoading(false);//将积极加载改为按需加载
        return configuration;
    }
}
