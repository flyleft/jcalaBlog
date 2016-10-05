package me.jcala.blog.service;

import me.jcala.blog.domain.Project;
import me.jcala.blog.mapping.ProjectMapper;
import me.jcala.blog.service.inter.ProjectSer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
@Service
public class ProjectSerImpl implements ProjectSer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSerImpl.class);
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Cacheable(value = "projects",condition = "#page==1",key = "1")
    public List<Project> getPros(int page) {
        List<Project> projectList = new ArrayList<>();
        int start = (page - 1) * 5;
        try {
            projectList = projectMapper.select(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return projectList;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "projects",key = "1"),
            @CacheEvict(value = "projectPageNum",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addPro(Project project) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        project.setDate(timestamp);
        try {
            projectMapper.insert(project);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Project> adminGetPros(int page) {
        int start = (page - 1) * 10;
        List<Project> projectList = new ArrayList<>();
        try {
            projectList = projectMapper.adminSelect(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return projectList;
    }

    @Override
    public int adminGetPageNum() {
        int count = 0;
        try {
            count = projectMapper.count();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return count % 10 == 0 ? count / 10 : count / 10 + 1;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "projects",key = "1"),
            @CacheEvict(value = "projectPageNum",key = "1")
    })
    public void deletePro(int id) {
        try {
            projectMapper.delete(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Project getProById(String idStr) {
        int id = 1;
        Project project = new Project();
        try {
            id = Integer.valueOf(idStr);
            project = projectMapper.selectById(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return project;
    }

    @Override
    @CacheEvict(value = "projects",key = "1")
    public void updatePro(Project project) {
        try {
            projectMapper.Update(project);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "projectPageNum",key = "1")
    public int getPageNum() {
        int count = 0;
        try {
            count = projectMapper.count();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return count % 5 == 0 ? count / 5 : count / 5 + 1;
    }
}
