package me.jcala.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 博客标签
 */
@Getter
@Setter
@NoArgsConstructor
public class BlogTag {
    private Integer tid;
    private String name;
    private List<BlogView> blogViews;
}
