package me.jcala.blog.domain;

import lombok.*;

/**
 * 个人信息实体
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Info {
    private String username;//用户名
    private String password;//md5加密后的密码
    private String email;//邮箱,默认为#
    private String github;//github地址，默认为#
    private String twitter;//twitter地址，默认为#
    private String md;//简历的markdown文本，为了admin管理时能够回显
    private String resume;//简历的html文本
    private String Avatar;//头像地址
}
