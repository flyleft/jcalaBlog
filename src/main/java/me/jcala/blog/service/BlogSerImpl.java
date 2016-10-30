package me.jcala.blog.service;

import me.jcala.blog.domain.Archive;
import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.BlogMapper;
import me.jcala.blog.service.inter.BlogSer;
import me.jcala.blog.utils.TimeTools;
import me.jcala.blog.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/3.
 */
@Service
public class BlogSerImpl implements BlogSer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogSerImpl.class);
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public BlogView adminGetBlog(int vid){
        BlogView blogView=null;
        try {
            blogView=blogMapper.selectAdmin(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogView;
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives"),
            @CacheEvict(value = "tagList"),
            @CacheEvict(value = "archivePageNum")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean addBlog(BlogView blogView){
        blogView.setDate(new Date(System.currentTimeMillis()));
        boolean result=true;
        try {
            blogMapper.insertBlog(blogView);
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
    public List<BlogView> getBlogPage(int id){
        List<BlogView> blogList=new ArrayList<>();
        try {
            int start=(id-1)*10;
            blogList=blogMapper.selectTenBlogs(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogList;
    }
    @Override
    public int getPageNum(){
        int num=0;
        try {
            num=blogMapper.selectBlogNum();
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
    }
    return num%10==0?num/10:num/10+1;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives"),
            @CacheEvict(value = "tagList")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
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
    @Caching(evict = {
            @CacheEvict(value = "archives"),
            @CacheEvict(value = "tagList"),
            @CacheEvict(value = "archivePageNum")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
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
    @Cacheable(value = "tagList")
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
    @Cacheable(value = "archives",condition = "#page==1")
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

    @Override
    @Cacheable(value = "archivePageNum")
    public int getArchiveNum() {
        int blogNum=0;
        try {
            blogNum=blogMapper.selectBlogNum();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogNum%12==0?blogNum/12:blogNum/12+1;
    }

    @Override
    public BlogView getBlog(int vid) {
        BlogView blogView=new BlogView();
        try {
            blogView=blogMapper.selectView(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogView;
    }

    @Override
    public BlogView getPrevBlog(int vid) {
        BlogView blogView=null;
        try {
            blogView=blogMapper.selectPreView(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogView;
    }

    @Override
    public BlogView getNextBlog(int vid) {
        BlogView blogView=null;
        try {
            blogView=blogMapper.selectNextView(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogView;
    }

    @Override
    public List<BlogView> getBlogByTag(String tagName) {
        List<BlogView> views=new ArrayList<>();
        try {
            List<Integer> vids=blogMapper.selectVidBytag(tagName);
            for (int vid:vids){
                BlogView view=blogMapper.selectTagView(vid);
                if (view!=null){
                    view.setVid(vid);
                    String monthDay=TimeTools.getEdate(view.getDate());
                    view.setMonthDay(monthDay);
                    views.add(view);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return views;
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
                archives.add(archive);
            }
        }
        return archives;
    }
    private void addViewTag(String tagStr,int vid) throws Exception{
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.insertViewTag(tag,vid);
        }

    }
    private void updateViewTag(String tagStr,int vid) throws Exception{
        blogMapper.deleteViewTag(vid);
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.insertViewTag(tag,vid);
        }

    }

}
