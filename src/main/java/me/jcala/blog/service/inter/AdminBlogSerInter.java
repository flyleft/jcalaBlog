package me.jcala.blog.service.inter;


import me.jcala.blog.domain.BlogView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public interface AdminBlogSerInter {
    int addBlog(BlogView blogView) throws Exception;
    BlogView getBlogByVid(int vid);
    List<BlogView> getBlogPage(int id);
    int getPageNum();
    void updateBlog(int id,BlogView blogView) throws Exception;
}
