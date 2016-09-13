package me.jcala.blog.utils;

import me.jcala.blog.interceptor.IpInterceptor;
import me.jcala.blog.mapping.VisiterMapper;
import me.jcala.blog.service.AdminBlogSer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by Administrator on 2016/9/13.
 */
@Component
@EnableScheduling
public class IpScheduleTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpScheduleTask.class);
    @Autowired
    private VisiterMapper visiterMapper;
    @Scheduled(cron = "0 0 0 * * ?")
    public void reportCurrentByCron(){
        int visiter=IpInterceptor.ips.size();
        Date date=new Date(System.currentTimeMillis());
        try {
            for(String day:IpInterceptor.ips){
                visiterMapper.insert(date,visiter);
            }
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        } finally {
            IpInterceptor.ips.clear();
        }
    }

}
