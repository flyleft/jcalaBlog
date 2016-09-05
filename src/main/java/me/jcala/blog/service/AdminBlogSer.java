package me.jcala.blog.service;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.AdminBlogMapper;
import me.jcala.blog.service.inter.AdminBlogSerInter;
import me.jcala.blog.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
@Service
public class AdminBlogSer implements AdminBlogSerInter{
    @Autowired
    private AdminBlogMapper adminBlogMapper;
    @Override
    public void addBlog(String title, String tags, String article)  throws Exception {
        BlogView blogView=new BlogView();
        blogView.setArticle(article);
        blogView.setTitle(title);
        blogView.setTags(tags);
        blogView.setDate(Tools.getTimestamp());
        adminBlogMapper.addBlog(blogView);
        addViewTag(tags,blogView.getVid());
    }
    public void addViewTag(String tagStr,int vid) throws Exception{
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            adminBlogMapper.addViewTag(tag,vid);
            adminBlogMapper.addTag(tag);
        }

    }
}
