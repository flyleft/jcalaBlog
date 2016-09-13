package me.jcala.blog.service;

import me.jcala.blog.domain.Profile;
import me.jcala.blog.mapping.ProfileMapper;
import me.jcala.blog.service.inter.ProfileSerInter;
import me.jcala.blog.utils.Md5Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class ProfileSer implements ProfileSerInter {
    private static final int MODIFYPASSSUC=0;//修改密码成功
    private static final int PASSERROE=1;//密码错误
    private static final int SySTEMERROE=2;//系统错误
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileSerInter.class);
    @Autowired
    private ProfileMapper profileMapper;
    @Override
    public Profile getProfile() {
        Profile profile=new Profile();
        try {
            profile=profileMapper.select();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return profile;
    }

    @Override
    public boolean login(Profile user) {
        int num=0;
        try {
            num=profileMapper.selectByPw(user.getUsername(), Md5Tools.MD5(user.getPassword()));
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
            num=profileMapper.selectByOldPass(Md5Tools.MD5(oldPass));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return num>0;
    }
    @Override
    public boolean updateProfile(Profile info) {
        boolean result=true;
        try {
            profileMapper.update(info);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            result=false;
        }
        return result;
    }

    @Override
    public int modifyPw(String oldPass,String newPass) {
        int resdult;
       if (checkPass(oldPass)){
           try {
               profileMapper.updataPass(Md5Tools.MD5(newPass));
               resdult=MODIFYPASSSUC;
           } catch (Exception e) {
               LOGGER.error(e.getMessage());
               resdult=SySTEMERROE;
           }
       }else {
          resdult=PASSERROE;
       }
        return resdult;
    }
    @Override
    public void addSession(HttpServletRequest request,Profile info){
        HttpSession session = request.getSession(true);
        session.setAttribute("cur_user",info);
        session.setMaxInactiveInterval(600);
    }
    @Override
    public void destroySession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("cur_user");
    }
}
