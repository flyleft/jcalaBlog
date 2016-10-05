package me.jcala.blog.conf;

import me.jcala.blog.interceptor.UserSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web页面配置类，拦截器地址在此注册
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter{
    @Autowired
    private UserSecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor).addPathPatterns("/admin/**");//配置登录拦截器拦截路径
    }

}
