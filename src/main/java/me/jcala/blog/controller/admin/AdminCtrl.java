package me.jcala.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {
    @GetMapping("/")
    public String moniter() {
        return "admin/moniter";
    }
    @GetMapping("/newBlog")
    public String newBlog() {
        return "admin/newBlog";
    }
    @PostMapping("/saveBlog")
    public String saveBlog(String title,String tags,String article) {
        System.out.println("title is:"+title);
        System.out.println("tags is:"+tags);
        System.out.println("article is:"+article);
        return "admin/newBlog";
    }
    @GetMapping("/blogSet")
    public String blogSet() {
        return "admin/blogSet";
    }
    @GetMapping("/blogRevise")
    public String blogRevise() {
        return "admin/blogRevise";
    }
    @GetMapping("/info")
    public String info() {
        return "admin/info";
    }
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }
}
