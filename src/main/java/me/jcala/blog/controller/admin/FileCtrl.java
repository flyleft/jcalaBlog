package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Info;
import me.jcala.blog.domain.UploadPic;
import me.jcala.blog.service.inter.FileUploadSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileCtrl {

    @Autowired
    private FileUploadSer uploadSer;

    @PostMapping("/admin/file/uplPic.action")
    @ResponseBody
    public UploadPic uploadPic(HttpServletRequest request) throws RuntimeException{
            return uploadSer.uploadPic(request);
    }

    @PostMapping("/admin/file/uplAva.action")
    public String uploadAva(HttpServletRequest request, Model model) throws RuntimeException{
        Info info=uploadSer.updateAvatar(request);
        model.addAttribute("info",info);
        return "admin/info";
    }
}
