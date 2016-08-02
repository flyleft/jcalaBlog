package me.jcala.blog.service;

import me.jcala.blog.domain.ArchivesYear;
import me.jcala.blog.domain.Project;
import me.jcala.blog.domain.Tag;
import me.jcala.blog.service.inter.BlogSerInter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BlogController中数据获取service
 * Created by Administrator on 2016/8/2.
 */
@Service
public class BlogSer implements BlogSerInter {
    /**
     * /archives页面数据的获取
     */
    @Override
    public List<ArchivesYear> archives() throws Exception {
        return null;
    }
    @Override
    public List<String> aboutMe() throws Exception {
        return null;
    }

    @Override
    public List<String> experience() throws Exception {
        return null;
    }

    @Override
    public List<Project> projects() throws Exception {
        return null;
    }

    @Override
    public List<Tag> tags() throws Exception {
        return null;
    }
}
