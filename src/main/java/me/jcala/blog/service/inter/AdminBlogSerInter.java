package me.jcala.blog.service.inter;


/**
 * Created by Administrator on 2016/9/3.
 */
public interface AdminBlogSerInter {
    int addBlog(String title,String tags,String article,String md) throws Exception;

}
