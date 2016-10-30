package me.jcala.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 前端页面archives显示的博客列表对应的实体
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Archive {
    private int year;//年份
    private List<BlogView> list;//此年份的博客列表
}
