package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    String userName;
    String email;
    String homePage;
    String githubName;
    String twitterName;
    String introduce;
    List<String> aboutMe;
    List<String> experience;
}
