package me.jcala.blog.service.inter;


import me.jcala.blog.domain.BlogView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public interface AdminBlogSerInter {
    boolean addBlog(BlogView blogView) throws Exception;
    BlogView getBlogByVid(int vid);
    List<BlogView> getBlogPage(int id);
    int getPageNum();
    boolean updateBlog(BlogView blogView) throws Exception;
    boolean deleteBlogById(int vid);
}
