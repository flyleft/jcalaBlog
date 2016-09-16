package me.jcala.blog.service;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.BlogMapper;
import me.jcala.blog.service.inter.BlogSerInter;
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
public class BlogSer implements BlogSerInter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogSer.class);
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public boolean addBlog(BlogView blogView){//String title, String tags, String article,String md
        blogView.setDate(Tools.getTimestamp());
        boolean result=true;
        try {
            blogMapper.addBlog(blogView);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            result=false;
        }
        if (result){
            try {
                addViewTag(blogView.getTags(),blogView.getVid());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                result=false;
            }
        }
        return result;
    }
    @Override
    public BlogView getBlogByVid(int vid){
        BlogView blogView=null;
        try {
            blogView=blogMapper.getBlogById(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogView;
    }
    @Override
    public List<BlogView> getBlogPage(int id){
        List<BlogView> blogList=new ArrayList<BlogView>();
        try {
            int start=(id-1)*8;
            blogList=blogMapper.getEightBlogs(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogList;
    }
    @Override
    public int getPageNum(){
        int num=0;
        try {
            num=blogMapper.getBlogNum();
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
    }
    int pageNum=num%8==0?num/8:num/8+1;
    return pageNum;
    }
    @Override
    public boolean updateBlog(BlogView blogView){
        boolean result=true;
        try {
            blogMapper.updateBlogView(blogView);
        } catch (Exception e) {
            result=false;
            LOGGER.error(e.getMessage());
        }
        if (result){
            try {
                updateViewTag(blogView.getTags(),blogView.getVid());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                result=false;
            }
        }
        return result;
    }
    private void addViewTag(String tagStr,int vid) throws Exception{
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.addViewTag(tag,vid);
        }

    }
    private void updateViewTag(String tagStr,int vid) throws Exception{
        blogMapper.deleteViewTag(vid);
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.addViewTag(tag,vid);
        }

    }

    @Override
    public boolean deleteBlogById(int vid) {
        boolean result=true;
        try {
            blogMapper.deleteBlogView(vid);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            result=false;
        }
        return result;
    }

    @Override
    public List<String> getTagList() {
        List<String> tags=new ArrayList<>();
        try {
            tags=blogMapper.selectTags();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return tags;
    }
}
