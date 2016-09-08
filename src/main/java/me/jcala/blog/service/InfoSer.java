package me.jcala.blog.service;

import me.jcala.blog.domain.Info;
import me.jcala.blog.mapping.InfoMapper;
import me.jcala.blog.service.inter.InfoSerInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class InfoSer implements InfoSerInter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoSer.class);
    @Autowired
    private InfoMapper infoMapper;
    @Override
    public Info getInfo() {
        Info info=new Info();
        try {
            info=infoMapper.select();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return info;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean modifyPw() {
        return false;
    }
}
