package me.jcala.blog.utils;

import me.jcala.blog.interceptor.IpInterceptor;
import me.jcala.blog.mapping.VisiterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2016/9/13.
 */
@Component
@EnableScheduling
public class ScheduleTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    @Autowired
    private VisiterMapper visiterMapper;

    /**
     * 定时任务
     * 每天晚上12点运行此程序
     * 将今天的访问人数插入数据库，并将存储IP地址的IpInterceptor的Set清空
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void reportCurrentByCron(){
        int visiter=IpInterceptor.ips.size();
        Date date=new Date(System.currentTimeMillis());
        try {
            visiterMapper.insert(date,visiter);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        } finally {
            IpInterceptor.ips.clear();
        }
    }

    /**
     * 定时任务
     * 每月1号凌晨12点运行
     * 把两个月之前的访问人数删除，防止数据积累过多，减少数据库压力
     */
    @Scheduled(cron = "0 0 0 1 * ?")
   private void deleteEarlierVt(){
        try {
            visiterMapper.delete(last2Month());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 获取当前月往前数前两个月的月份
     */
   private String last2Month(){
       java.util.Date date = new java.util.Date();//当前日期
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对象
       Calendar calendar = Calendar.getInstance();//日历对象
       calendar.setTime(date);//设置当前日期
       calendar.add(Calendar.MONTH, -2);
       return sdf.format(calendar.getTime());
   }
}
