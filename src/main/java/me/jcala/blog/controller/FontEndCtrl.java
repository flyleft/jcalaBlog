package me.jcala.blog.controller;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.service.inter.BlogSerInter;
import me.jcala.blog.service.inter.InfoSerInter;
import me.jcala.blog.service.inter.ProjectSerInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FontEndCtrl {
    @Autowired
    private BlogSerInter blogSer;
    @Autowired
    private ProjectSerInter projectSer;
    @Autowired
    private InfoSerInter infoSer;
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
        model.addAttribute("projects",projectSer.getPros(page));
        model.addAttribute("pageNum",projectSer.getPageNum());
        model.addAttribute("current",page);
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        model.addAttribute("tags",blogSer.getTagList());
        return "tags";
    }
    @GetMapping("/tags/{tagName}")
    public String tagName(@PathVariable String tagName,Model model) {
        List<BlogView> views=blogSer.getBlogByTag(tagName);
        model.addAttribute("views",views);
        model.addAttribute("tagName",tagName);
        return "tagView";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("resume",infoSer.getResumeView());
        model.addAttribute("avatar","http://127.0.0.1:8090/img/20168/1474798900267.jpg");
        return "about";
    }
    @GetMapping("/post/{id}")
    public String post(@PathVariable int id,Model model) {
        BlogView blogView=blogSer.getBlog(id);
        BlogView prev=blogSer.getPrevBlog(id);
        BlogView next=blogSer.getNextBlog(id);
        model.addAttribute("prev",prev);
        model.addAttribute("next",next);
        model.addAttribute("article",blogView.getArticle());
        return "post";
    }
    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}
