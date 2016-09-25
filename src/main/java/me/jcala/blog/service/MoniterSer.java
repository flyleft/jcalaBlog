package me.jcala.blog.service;

import com.sun.management.OperatingSystemMXBean;
import me.jcala.blog.domain.Visiter;
import me.jcala.blog.mapping.VisiterMapper;
import me.jcala.blog.service.inter.MoniterSerInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
@Service
public class MoniterSer implements MoniterSerInter{
    private static final Logger LOGGER = LoggerFactory.getLogger(MoniterSer.class);
    private static final int CPUTIME = 30;
    private static final int PERCENT = 100;
    private static final int FAULTLENGTH = 10;
    private static final File versionFile = new File("/proc/version");
    private static String linuxVersion = null;
    @Autowired
    private VisiterMapper visiterMapper;
    @Override
    public List<Visiter> getVisiters() {
        List<Visiter> visiters=new ArrayList<>();
        try {
            visiters=visiterMapper.select();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return visiters;
    }
    @Override
   public int getFreeMemery(){
           OperatingSystemMXBean osmxb = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
           long totalvirtualMemory = osmxb.getTotalSwapSpaceSize(); // 剩余的物理内存
           long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
           Double compare = (Double) (freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
           return compare.intValue();
   }
}
