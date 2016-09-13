package me.jcala.blog.service.inter;

import me.jcala.blog.domain.Profile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface ProfileSerInter {
    Profile getProfile();
    boolean login(Profile profile);
    int modifyPw(String oldPass,String newPass);
    boolean updateProfile(Profile profile);
    boolean checkPass(String oldPass);
    void addSession(HttpServletRequest request, Profile profile);
    void destroySession(HttpServletRequest request);
}
