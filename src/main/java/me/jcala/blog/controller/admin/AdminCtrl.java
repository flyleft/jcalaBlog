package me.jcala.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {
    @GetMapping("/moniter")
    public String moniter() {
        return "admin/moniter";
    }
    @GetMapping("/newBlog")
    public String newBlog() {
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
