package me.jcala.blog.service;

import me.jcala.blog.domain.Project;
import me.jcala.blog.service.inter.ProjectSerInter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
@Service
public class ProjectSer implements ProjectSerInter {
    @Override
    public List<Project> getPjList() {
        return null;
    }
}
