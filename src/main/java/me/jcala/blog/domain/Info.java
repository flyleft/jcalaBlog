package me.jcala.blog.domain;

import lombok.*;

/**
 * Created by Administrator on 2016/9/8.
 */
@Setter
@Getter
@NoArgsConstructor
public class Info {
    private String username;
    private String password;
    private String email;
    private String github;
    private String twitter;
    private String md;
    private String resume;
}
