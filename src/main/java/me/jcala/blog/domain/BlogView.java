package me.jcala.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 博客页面的存储，包括id,日期，标题和博客页面
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogView {
    private Integer vid;
    private Timestamp date;
    private String title;
    private String article;
    private String tags;
}
