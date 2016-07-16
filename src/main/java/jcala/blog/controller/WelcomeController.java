package jcala.blog.controller;

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

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("username","jcala");
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
