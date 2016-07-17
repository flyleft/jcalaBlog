package jcala.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String archives(){
        return "archives";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/projects")
    public String projects() {
        return "projects";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }
}
