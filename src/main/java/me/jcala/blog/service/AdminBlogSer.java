package me.jcala.blog.service;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.AdminBlogMapper;
import me.jcala.blog.service.inter.AdminBlogSerInter;
import me.jcala.blog.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private void addViewTag(String tagStr,int vid) throws Exception{
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            adminBlogMapper.addViewTag(tag,vid);
            adminBlogMapper.addTag(tag);
        }

    }
}
