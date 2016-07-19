package jcala.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import jcala.blog.entity.Tag;


/**
 * Created by jcala on 2016/7/14
 */
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
