package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

/**
 * 博客页面的存储，包括id,日期，标题和博客页面
 */
@Getter
@Setter
@NoArgsConstructor
public class BlogView {
    private Integer vid;
    private Date date;
    private String title;
    private String article;
    private String tags;
    private String md;
    private String monthDay;
    public BlogView(String title,String tags,String md){
        this.title=title;
        this.tags=tags;
        this.md=md;
    }
    public BlogView(int vid,String title,String tags){
        this.vid=vid;
        this.title=title;
        this.tags=tags;
    }
}
