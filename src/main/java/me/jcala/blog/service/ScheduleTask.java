package me.jcala.blog.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/13.
 */
@EnableScheduling
public class ScheduleTask {
  /*  private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    @Autowired
    private VisiterMapper visiterMapper;

     // 定时任务
     //每天晚上12点运行此程序
     //将今天的访问人数插入数据库，并将存储IP地址的IpInterceptor的Set清空

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

     //定时任务
     //每月1号凌晨12点运行
     //把两个月之前的访问人数删除，防止数据积累过多，减少数据库压力

    @Scheduled(cron = "0 0 0 1 * ?")
   private void deleteEarlierVt(){
        try {
            visiterMapper.delete(TimeTools.last2Month());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
*/}
