package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/9/16.
 */
@Setter
@Getter
@NoArgsConstructor
public class Project {
    private String name;
    private String url;
    private String tech;
    private String desp;
    private Timestamp date;
}
