package me.jcala.blog.controller.admin;

import me.jcala.blog.service.AdminBlogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {
    @Autowired
    private AdminBlogSer adminBlogSer;
    @GetMapping("/")
    public String moniter() {
        return "admin/moniter";
    }
    @GetMapping("/newBlog")
    public String newBlogBefore() {
        return "admin/newBlog";
    }
    @GetMapping("/newBlog/{id}")
    public String newBlogAfter(@PathVariable("id") String id,Model model) {
       if ("1".equals(id)){
          model.addAttribute("myId",1);
       }
       else{
           model.addAttribute("myId",2);
       }
        return "admin/newBlog";
    }
    @PostMapping("/saveBlog")
    public ModelAndView saveBlog(String title,String tags,String article,String markdown) {
        int id=0;
        System.out.println("title:"+title);
        System.out.println("tags:"+tags);
        System.out.println("article:"+article);
        System.out.println("markdown:"+markdown);
        /*try {
            adminBlogSer.addBlog(title,tags,article);
            id=1;
        } catch (Exception e) {
            id=2;
           System.out.println("============="+e.getMessage());
        }*/
        return new ModelAndView("redirect:/html/newBlog/"+id);
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
