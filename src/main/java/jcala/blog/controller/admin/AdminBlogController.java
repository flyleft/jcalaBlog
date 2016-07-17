package jcala.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")//
public class AdminBlogController {
    @GetMapping("/mkEditor")
    public String mkEditor() {
        return "admin/mkEditor";
    }
}
