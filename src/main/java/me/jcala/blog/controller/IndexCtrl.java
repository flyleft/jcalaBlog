package me.jcala.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jcala on 2016/7/10
 */
@Controller
public class IndexCtrl {
    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}
