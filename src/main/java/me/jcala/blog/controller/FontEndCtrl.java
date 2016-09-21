package me.jcala.blog.controller;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.service.BlogSer;
import me.jcala.blog.service.InfoSer;
import me.jcala.blog.service.ProjectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FontEndCtrl {
    @Autowired
    private BlogSer blogSer;
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private InfoSer infoSer;
    @GetMapping("/archives/{page}")
    public String archives(@PathVariable int page,Model model){
        model.addAttribute("archives",blogSer.getArchive(page));
        model.addAttribute("pageNum",blogSer.getArchiveNum());
        model.addAttribute("current",page);
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        return "projects";
    }
    @GetMapping("/projects/{page}")
    public String projectPage(@PathVariable int page,Model model) {
        model.addAttribute("projects",projectSer.getFivePros(page));
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        model.addAttribute("tags",blogSer.getTagList());
        return "tags";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("resume",infoSer.getResumeView());
        return "about";
    }
    @GetMapping("/post/{id}")
    public String post(@PathVariable int id,Model model) {
        BlogView blogView=blogSer.getBlog(id);
        model.addAttribute("article",blogView.getArticle());
        return "post";
    }
    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}
