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

@Service
public class ProjectSerImpl implements ProjectSer {

    private ProjectMapper projectMapper;

    @Autowired
    public ProjectSerImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    @Cacheable(value = "projects",condition = "#page==1",key = "1")
    public List<Project> getPros(int page) throws RuntimeException{
        int start = (page - 1) * 5;
        return projectMapper.select(start);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "projects",key = "1"),
            @CacheEvict(value = "projectPageNum",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addPro(Project project) throws RuntimeException{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        project.setDate(timestamp);
        projectMapper.insert(project);
    }

    @Override
    public List<Project> adminGetPros(int page) throws RuntimeException{
        int start = (page - 1) * 10;
        return projectMapper.adminSelect(start);
    }

    @Override
    public int adminGetPageNum() throws RuntimeException{
        int count = projectMapper.count();
        return count % 10 == 0 ? count / 10 : count / 10 + 1;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "projects",key = "1"),
            @CacheEvict(value = "projectPageNum",key = "1")
    })
    public void deletePro(int id) throws RuntimeException{
            projectMapper.delete(id);
    }

    @Override
    public Project getProById(String idStr) throws RuntimeException{
        int id = Integer.valueOf(idStr);
        return  projectMapper.selectById(id);
    }

    @Override
    @CacheEvict(value = "projects",key = "1")
    public void updatePro(Project project) throws RuntimeException {
            projectMapper.Update(project);
    }

    @Override
    @Cacheable(value = "projectPageNum",key = "1")
    public int getPageNum() throws RuntimeException{
        int count = projectMapper.count();
        return count % 5 == 0 ? count / 5 : count / 5 + 1;
    }
}
