package jcala.blog.controller;

import jcala.blog.entity.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jcala.blog.entity.Tag;
@Controller
public class BlogController {
    @GetMapping("/blogs")
    public String blogs() {
        return "blogs";
    }

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("headerH1","Archives");
        model.addAttribute("headerP","博客列表");
        return "archives";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("headerH1","About");
        model.addAttribute("headerP","关于我自己");
        return "about";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("headerH1","projects");
        model.addAttribute("headerP","我的博客列表");
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
        model.addAttribute("headerH1","Tags");
        model.addAttribute("headerP","博客标签");
        List<Tag> tags=new ArrayList<>();
        tags.add(new Tag("home","/#home"));
        tags.add(new Tag("blogs","/blogs"));
        tags.add(new Tag("projects","/projects"));
        tags.add(new Tag("about","/about"));
        model.addAttribute("tags",tags);
        return "tags";
    }
}
