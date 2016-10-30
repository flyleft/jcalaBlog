package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * 博客页面的存储，包括id,日期，标题和博客页面
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogView {
    private Integer vid;//id
    private Date date;//博客创建日期
    private String title;//博客标题，不可为空
    private String article;//博客内容的html文本
    private String tags;//标签，不同标签以,隔开
    private String md;//博客内容的markdown文本
    private String monthDay;//形如"Oct 04",为了方便archives页面显示，并不对应数据库的任何一列
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
