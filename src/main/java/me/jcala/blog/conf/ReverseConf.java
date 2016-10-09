package me.jcala.blog.conf;

import me.jcala.blog.domain.ReversePath;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created by jcala on 2016/7/29
 */
@Configuration
public class ReverseConf {
    @Value("${reverse.domain}")
    private String reverse_domain;//反向代理服务器比如nginx访问根路径
    @Value("${reverse.root}")
    private String reverse_root;//反向代理服务器比如nginx设置的根路径

    @Bean(name = "reversePathBean")
    public ReversePath getReverse(){
        ReversePath path=new ReversePath();
        path.setPicFilePath(reverse_root+ File.separatorChar+"img"+File.separatorChar);
        path.setPicUrlPath(reverse_domain+"/img/");
        path.setCssUrlPath(reverse_domain+"/css/");
        path.setJsUrlPath(reverse_domain+"/js/");
        return path;
    }
}