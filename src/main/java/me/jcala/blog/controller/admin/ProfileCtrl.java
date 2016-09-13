package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Profile;
import me.jcala.blog.service.ProfileSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/8.
 */
@Controller
public class ProfileCtrl {
    @Autowired
    private ProfileSer profileSer;
    @GetMapping("/admin/profile")
    public String profile(Model model,HttpServletRequest request) throws Exception {
        Profile profile=profileSer.getProfile();
        model.addAttribute("profile",profile);
        String result=request.getParameter("result");
        if (result!=null){
            model.addAttribute("result",Integer.parseInt(result));
        }else {
            model.addAttribute("result",3);
        }
        return "admin/profile";
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
    public String doLogin(Profile user, HttpServletRequest request){
        boolean result=profileSer.login(user);
        if (result){
            profileSer.addSession(request,user);
            return "redirect:/admin";
        }else {
            return "redirect:/login?result=fail";
        }
    }
    @GetMapping("/logout")
    public  String logout(HttpServletRequest request){
        profileSer.destroySession(request);
        return "redirect:/login";
    }

    @PostMapping("/admin/profile.do")
    public String updateInfo(Profile info, Model model){
        boolean result=profileSer.updateProfile(info);
        model.addAttribute("targetUrl","/admin/profile");
        if (result){
            model.addAttribute("result",1);
            return "admin/result";
        }else {
            model.addAttribute("result",0);
            return "admin/result";
        }
    }
    @PostMapping("/admin/pass.do")
    public  String passModify(String old_pass,String new_pass,HttpServletRequest request){
        int result=profileSer.modifyPw(old_pass,new_pass);
        if (result==0){
            profileSer.destroySession(request);
        }
        return "redirect:/admin/info?result="+result;
    }
}
