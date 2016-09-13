package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Info;
import me.jcala.blog.service.InfoSer;
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
public class AdminInfoCtrl {
    @Autowired
    private InfoSer infoSer;
    @GetMapping("/admin/info")
    public String info(Model model,HttpServletRequest request) throws Exception {
        Info info=infoSer.getInfo();
        model.addAttribute("info",info);
        String result=request.getParameter("result");
        if (result!=null){
            model.addAttribute("result",Integer.parseInt(result));
        }else {
            model.addAttribute("result",3);
        }
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
    public String doLogin(Info user,HttpServletRequest request){
        boolean result=infoSer.login(user);
        if (result){
            infoSer.addSession(request,user);
            return "redirect:/admin";
        }else {
            return "redirect:/login?result=fail";
        }
    }
    @GetMapping("/logout")
    public  String logout(HttpServletRequest request){
        infoSer.destroySession(request);
        return "redirect:/login";
    }

    @PostMapping("/admin/info.action")
    public String updateInfo(Info info,Model model){
        boolean result=infoSer.updateInfo(info);
        model.addAttribute("targetUrl","/admin/info");
        if (result){
            model.addAttribute("result",1);
            return "admin/result";
        }else {
            model.addAttribute("result",0);
            return "admin/result";
        }
    }
    @PostMapping("/admin/pass.action")
    public  String passModify(String old_pass,String new_pass,HttpServletRequest request){
        int result=infoSer.modifyPw(old_pass,new_pass);
        if (result==0){
            infoSer.destroySession(request);
        }
        return "redirect:/admin/info?result="+result;
    }
}
