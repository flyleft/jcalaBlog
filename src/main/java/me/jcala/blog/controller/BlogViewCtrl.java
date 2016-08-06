package me.jcala.blog.controller;

import me.jcala.blog.domain.ArchivesYear;
import me.jcala.blog.domain.Project;
import me.jcala.blog.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BlogViewCtrl {
    @GetMapping("/archives")
    public String archives(Model model){
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
        model.addAttribute("archivesYears",archivesYears);
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
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
        model.addAttribute("projects",projects);
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        List<Tag> tags=new ArrayList<>();
        tags.add(new Tag("home","/#home"));
        tags.add(new Tag("blogs","/blogs"));
        tags.add(new Tag("projects","/projects"));
        tags.add(new Tag("about","/about"));
        model.addAttribute("tags",tags);
        return "tags";
    }
}
