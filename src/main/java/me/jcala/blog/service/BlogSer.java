package me.jcala.blog.service;

import me.jcala.blog.domain.Archive;
import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.BlogMapper;
import me.jcala.blog.service.inter.BlogSerInter;
import me.jcala.blog.utils.TimeTools;
import me.jcala.blog.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

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
        blogView.setDate(new Date(System.currentTimeMillis()));
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
        List<BlogView> blogList=new ArrayList<>();
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

    @Override
    public List<Archive> getArchive(int page) {
        int start=(page-1)*12;
        List<Archive> archives=new ArrayList<>();
        try {
            List<BlogView> blogViews=blogMapper.selectArc(start);
            archives=bv2Ar(blogViews);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return archives;
    }
    private List<Archive> bv2Ar(List<BlogView> views) throws Exception{
        List<Archive> archives=new ArrayList<>();
        Map<Integer,Archive> years2Ar=new HashMap<>();
        for (BlogView view:views){
            Date date=view.getDate();
            view.setMonthDay(TimeTools.getEdate(date));
            int year=TimeTools.getYear(date);
            if (years2Ar.containsKey(year)){
                years2Ar.get(year).getList().add(view);
            }else {
                Archive archive=new Archive(year,new ArrayList<BlogView>());
                years2Ar.put(year,archive);
                archive.getList().add(view);
            }
        }
        return archives;
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

}
