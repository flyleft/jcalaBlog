package me.jcala.blog.controller;

import me.jcala.blog.service.WelcomeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by jcala on 2016/7/10
 */
@Controller
public class WelcomeController {
    @Autowired
   WelcomeSer welcomeSer;
    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("username",welcomeSer.getUsername());
        return "index";
    }
}
