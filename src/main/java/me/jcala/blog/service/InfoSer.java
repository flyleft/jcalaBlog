package me.jcala.blog.service;

import me.jcala.blog.domain.Info;
import me.jcala.blog.mapping.InfoMapper;
import me.jcala.blog.service.inter.InfoSerInter;
import me.jcala.blog.utils.Md5Tools;
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
    public boolean login(Info user) {
        int num=0;
        try {
            num=infoMapper.selectByPw(user.getUsername(), Md5Tools.MD5(user.getPassword()));
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
        if (num>0){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public boolean checkPass(String oldPass){
        int num=0;
        try {
            num=infoMapper.selectByOldPass(oldPass);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return num>0;
    }
    @Override
    public boolean updateInfo(Info info) {
        boolean result=true;
        try {
            infoMapper.update(info);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            result=false;
        }
        return result;
    }

    @Override
    public boolean modifyPw() {
        return false;
    }
}
