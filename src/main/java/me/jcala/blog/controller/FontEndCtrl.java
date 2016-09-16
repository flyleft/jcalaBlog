package me.jcala.blog.controller;

import me.jcala.blog.service.TempSer;
import me.jcala.blog.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FontEndCtrl {
    @Autowired
    private UserSer userSer;
    @Autowired
    private TempSer tempSer;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archivesYears",tempSer.archives());
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects",tempSer.projects());
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {

        return "tags";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("userInfo",userSer.getUserInfo());
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
