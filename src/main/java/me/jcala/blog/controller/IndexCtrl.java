package me.jcala.blog.controller;

import me.jcala.blog.service.IndexSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Created by jcala on 2016/7/10
 */
@Controller
public class IndexCtrl {
    @Autowired
    IndexSer indexSer;
    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("username", indexSer.getUsername());
        return "index";
    }
}
