package me.jcala.blog.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by jcala on 2016/7/29
 */
@Configuration
public class ReverseConf {
    @Value("${reverse.domain}")
    private String reverse_domain;
    @Value("${reverse.port}")
    private String reverse_port;
    @Value("${reverse.prefix}")
    private String reverse_prefix;
    @Value("${reverse.path}")
    private String reverse_path;
    @Bean
    public String getReverse(){
        return reverse_domain+reverse_port;
    }
}