package me.jcala.blog.controller.user;

import me.jcala.blog.service.user.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2016/8/2.
 */
@Controller
public class UserCtrl {
    @Autowired
    private UserSer userSer;
    @GetMapping("/about")
    public String about(Model model) {
      model.addAttribute("userInfo",userSer.userInfo());
        return "about";
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

}
