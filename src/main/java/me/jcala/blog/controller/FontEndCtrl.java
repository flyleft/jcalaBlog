package me.jcala.blog.controller;

import me.jcala.blog.service.BlogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FontEndCtrl {
    @Autowired
    private BlogSer blogSer;
    @GetMapping("/archives")
    public String archives(Model model){
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        model.addAttribute("tags",blogSer.getTagList());
        return "tags";
    }
    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
    @GetMapping("/post")
    public String post() {
        return "post";
    }
    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}
