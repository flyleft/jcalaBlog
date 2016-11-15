package me.jcala.blog.service;

import lombok.extern.slf4j.Slf4j;
import me.jcala.blog.domain.Archive;
import me.jcala.blog.domain.BlogView;
import me.jcala.blog.mapping.BlogMapper;
import me.jcala.blog.service.inter.BlogSer;
import me.jcala.blog.utils.TimeTools;
import me.jcala.blog.utils.Tools;
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

@Service
@Slf4j
public class BlogSerImpl implements BlogSer {
    private BlogMapper blogMapper;

    @Autowired
    public BlogSerImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public BlogView adminGetBlog(int vid){
        return blogMapper.selectAdmin(vid);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives",key = "1"),
            @CacheEvict(value = "tagList",key = "1"),
            @CacheEvict(value = "archivePageNum",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean addBlog(BlogView blogView){
        blogView.setDate(new Date(System.currentTimeMillis()));
        boolean result=true;
        try {
            blogMapper.insertBlog(blogView);
        } catch (Exception e) {
            log.error(e.getMessage());
            result=false;
        }
        if (result){
            try {
                addViewTag(blogView.getTags(),blogView.getVid());
            } catch (Exception e) {
                log.error(e.getMessage());
                result=false;
            }
        }
        return result;
    }
    @Override
    public List<BlogView> getBlogPage(int id){
            int start=(id-1)*10;
        return blogMapper.selectTenBlogs(start);
    }
    @Override
    public int adminGetPageNum(){
        int num=blogMapper.selectBlogNum();
    return num%10==0?num/10:num/10+1;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives",key = "1"),
            @CacheEvict(value = "tagList",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updateBlog(BlogView blogView){
        boolean result=true;
        try {
            blogMapper.updateBlogView(blogView);
        } catch (Exception e) {
            result=false;
            log.error(e.getMessage());
        }
        if (result){
            try {
                updateViewTag(blogView.getTags(),blogView.getVid());
            } catch (Exception e) {
                log.error(e.getMessage());
                result=false;
            }
        }
        return result;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives",key = "1"),
            @CacheEvict(value = "tagList",key = "1"),
            @CacheEvict(value = "archivePageNum",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean deleteBlogById(int vid) {
        boolean result=true;
        try {
            blogMapper.deleteBlogView(vid);
        } catch (Exception e) {
           log.error(e.getMessage());
            result=false;
        }
        return result;
    }

    @Override
    @Cacheable(value = "tagList",key = "1")
    public List<String> getTagList(){
        return blogMapper.selectTags();
    }

    @Override
    @Cacheable(value = "archives",condition = "#page==1",key = "1")
    public List<Archive> getArchive(int page){
        int start=(page-1)*12;
        return bv2Ar(blogMapper.selectArc(start));
    }

    @Override
    @Cacheable(value = "archivePageNum",key = "1")
    public int getArchiveNum(){
        int blogNum=blogMapper.selectBlogNum();
        return blogNum%12==0?blogNum/12:blogNum/12+1;
    }

    @Override
    public BlogView getBlog(int vid){
        return blogMapper.selectView(vid);
    }

    @Override
    public BlogView getPrevBlog(int vid){
        return blogMapper.selectPreView(vid);
    }

    @Override
    public BlogView getNextBlog(int vid) {
        BlogView blogView=null;
        try {
            blogView=blogMapper.selectNextView(vid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return blogView;
    }

    @Override
    public List<BlogView> getBlogByTag(String tagName){
        List<BlogView> views=new ArrayList<>();
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
        return views;
    }

    private List<Archive> bv2Ar(List<BlogView> views){
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
    private void addViewTag(String tagStr,int vid){
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.insertViewTag(tag,vid);
        }

    }
    private void updateViewTag(String tagStr,int vid){
        blogMapper.deleteViewTag(vid);
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.insertViewTag(tag,vid);
        }

    }

}
