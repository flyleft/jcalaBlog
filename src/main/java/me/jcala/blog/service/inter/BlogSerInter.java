package me.jcala.blog.service.inter;


import me.jcala.blog.domain.Archive;
import me.jcala.blog.domain.BlogView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public interface BlogSerInter {
    boolean addBlog(BlogView blogView) throws Exception;
    BlogView adminGetBlog(int vid);
    List<BlogView> getBlogPage(int id);
    int getPageNum();
    boolean updateBlog(BlogView blogView) throws Exception;
    boolean deleteBlogById(int vid);
    List<String> getTagList();
    List<Archive> getArchive(int page);
    int getArchiveNum();
    BlogView getBlog(int vid);
}
