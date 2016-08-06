package me.jcala.blog.controller;

import me.jcala.blog.service.BlogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogViewCtrl {
    @Autowired
    private BlogSer blogSer;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archivesYears",blogSer.archives());
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects",blogSer.projects());
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {

        model.addAttribute("tags",blogSer.tags());
        return "tags";
    }
}
