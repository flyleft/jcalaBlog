package me.jcala.blog.service.user;

import lombok.val;
import me.jcala.blog.domain.UserInfo;
import me.jcala.blog.service.inter.UserSerInter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/2.
 */
@Service
@PropertySource("classpath:user.properties")
public class UserSer implements UserSerInter {
    @Value("${info.userName}")
    private String userName;
    @Value("${info.email}")
    private String email;
    @Value("${info.homePage}")
    private String homePage;
    @Value("${info.githubName}")
    private String githubName;
    @Value("${info.twitterName}")
    private String twitterName;
    @Value("${info.introduce}")
    private String introduce;
    @Override
    public UserInfo getUserInfo() {
        val userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setEmail(email);
        userInfo.setHomePage(homePage);
        userInfo.setGithubName(githubName);
        userInfo.setTwitterName(twitterName);
        userInfo.setIntroduce(introduce);
        return userInfo;
    }
}
