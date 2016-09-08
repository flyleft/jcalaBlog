package me.jcala.blog.service;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.AdminBlogMapper;
import me.jcala.blog.service.inter.AdminBlogSerInter;
import me.jcala.blog.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
@Service
public class AdminBlogSer implements AdminBlogSerInter{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminBlogSer.class);
    @Autowired
    private AdminBlogMapper adminBlogMapper;
    @Override
    public int addBlog(String title, String tags, String article,String md)  throws Exception {
        BlogView blogView=new BlogView();
        blogView.setArticle(article);
        blogView.setTitle(title);
        blogView.setTags(tags);
        blogView.setMd(md);
        blogView.setDate(Tools.getTimestamp());
        adminBlogMapper.addBlog(blogView);
        addViewTag(tags,blogView.getVid());
        return blogView.getVid();
    }
    public BlogView getBlogByVid(int vid){
        BlogView blogView=null;
        try {
            blogView=adminBlogMapper.getBlogById(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogView;
    }
    public List<BlogView> getBlogPage(int id){
        List<BlogView> blogList=new ArrayList<BlogView>();
        try {
            int start=(id-1)*8;
            blogList=adminBlogMapper.getEightBlogs(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogList;
    }
    public int getPageNum(){
        int num=0;
        try {
            num=adminBlogMapper.getBlogNum();
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
    }
    int pageNum=num%8==0?num/8:num/8+1;
    return pageNum;
    }
    public void updateBlog(int id,String title,String tags,
                           String article,String md) throws Exception{
            BlogView blogView=new BlogView();
            blogView.setArticle(article);
            blogView.setTitle(title);
            blogView.setTags(tags);
            blogView.setMd(md);
            blogView.setVid(id);
            adminBlogMapper.updateBlogById(blogView);
    }
    private void addViewTag(String tagStr,int vid) throws Exception{
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            adminBlogMapper.addViewTag(tag,vid);
            adminBlogMapper.addTag(tag);
        }

    }
}
