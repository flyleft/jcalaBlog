package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/9/16.
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Project {
    private int id;//id
    private String name;//项目名称
    private String url;//项目url地址，例如https://github.com/jcalaz/jcalaBlog
    private String tech;//项目所用技术
    private String desp;//项目描述
    private Timestamp date;//项目创建时间
}
