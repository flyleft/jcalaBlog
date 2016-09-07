package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 博客页面的存储，包括id,日期，标题和博客页面
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogView {
    private Integer vid;
    private Timestamp date;
    private String title;
    private String article;
    private String tags;
    private String md;
}
