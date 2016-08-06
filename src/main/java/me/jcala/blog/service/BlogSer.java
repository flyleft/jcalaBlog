package me.jcala.blog.service;

import me.jcala.blog.domain.ArchivesYear;
import me.jcala.blog.domain.Project;
import me.jcala.blog.domain.Tag;
import me.jcala.blog.service.inter.BlogSerInter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public List<ArchivesYear> archives(){
        ArchivesYear.Archive archive2=new ArchivesYear.Archive(2,"","scala的AKKA使用指南");
        ArchivesYear.Archive archive1=new ArchivesYear.Archive(8,"","Spark与Hadoop");
        List<ArchivesYear.Archive> archives = new ArrayList<>();
        archives.add(archive1);
        archives.add(archive2);
        ArchivesYear archivesYear1=new ArchivesYear(2015,archives);
        ArchivesYear.Archive archive4=new ArchivesYear.Archive(1,"","Finagle使用指南");
        ArchivesYear.Archive archive3=new ArchivesYear.Archive(7,"","Guava教程");
        List<ArchivesYear.Archive> archives1 = new ArrayList<>();
        archives1.add(archive3);
        archives1.add(archive4);
        ArchivesYear archivesYear2=new ArchivesYear(2016,archives1);
        List<ArchivesYear> archivesYears=new ArrayList<>();
        archivesYears.add(archivesYear2);
        archivesYears.add(archivesYear1);
        return archivesYears;
    }
    @Override
    public List<Project> projects() {
        List<Project> projects=new ArrayList<>();
        Project project=new Project();
        project.setName("JcalaBlog");
        project.setShowHref("https://jcala.me/projects#jcalaBlog");
        project.setOpenSourceHref("https://github.com/jcalaz/jcalaBlog");
        project.setProjectDate(new Date());
        project.setSummary("SpringBlog is a very simple and clean-design blog system implemented with " +
                "Spring Boot. It is one of my learning projects to explore awesome features in Spring " +
                "Boot web programming. It is also the source code of my blog site ");
        project.setTechnology("Spring Boot/MVC/JPA + Hibernate + MySQL + Redis + Bootstrap + Jade");
        projects.add(project);
        return projects;
    }

    @Override
    public List<Tag> tags() {
        List<Tag> tags=new ArrayList<>();
        tags.add(new Tag("home","/#home"));
        tags.add(new Tag("blogs","/blogs"));
        tags.add(new Tag("projects","/projects"));
        tags.add(new Tag("about","/about"));
        return tags;
    }
}
