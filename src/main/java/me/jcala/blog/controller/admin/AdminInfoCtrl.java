package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Info;
import me.jcala.blog.service.InfoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/8.
 */
@Controller
@RequestMapping("/admin")
public class AdminInfoCtrl {
    @Autowired
    private InfoSer infoSer;
    @GetMapping("/info")
    public String info(Model model) {
        Info info=infoSer.getInfo();
        model.addAttribute("info",info);
        return "admin/info";
    }
    @GetMapping("/login")
    public String login(HttpServletRequest request,Model model){
        String result=request.getParameter("result");
        if (result!=null&&result.equals("fail")){
           model.addAttribute("yesOrWrong",0);
        }else {
            model.addAttribute("yesOrWrong",1);
        }
        return "admin/login";
    }
    @PostMapping("/login.action")
    public String doLogin(String username, String password){
        boolean result=infoSer.login(username,password);
        if (!result){
            return "redirect:/admin/login?result=fail";
        }else {
            return "redirect:/admin/";
        }
    }
}
