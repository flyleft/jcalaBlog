package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/8.
 */
@Setter
@Getter
@NoArgsConstructor
public class Info {
    private String username;
    private String password;
    private int sex;
    private String real_name;
    private String email;
    private String website;
    private String github;
    private String linkedin;
    private String twitter;
    private String pro_exp;
    private String edu_exp;
    private String advantage;
    public Info(String username, int sex, String real_name,
                String email, String website, String github,
                String linkedin, String twitter, String pro_exp,
                String edu_exp, String advantage){
        this.username=username;
        this.sex=sex;
        this.real_name=real_name;
        this.email=email;
        this.website=website;
        this.github=github;
        this.linkedin=linkedin;
        this.twitter=twitter;
        this.pro_exp=pro_exp;
        this.edu_exp=edu_exp;
        this.advantage=advantage;
    }
    public Info(String username,String password){
        this.username=username;
        this.password=password;
    }
}
