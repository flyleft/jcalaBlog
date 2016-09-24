package me.jcala.blog.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jcala.blog.domain.UploadPic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/9/24.
 */
@Controller
public class AdminFileCtrl {
    //设置文件上传目录为操作系统目录/blog/pic
    private static final String PICDIR=System.getProperties().getProperty("user.home")+
            File.separatorChar+"blog"+File.separatorChar+"pic";
    @PostMapping("/admin/file/uplPic.action")
    public void uploadPic(HttpServletRequest request,HttpServletResponse response) throws Exception{
        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
        //获得文件原始名称
        String fileName = multipartFile.getOriginalFilename();
        File path=new File(PICDIR);
        File targetFile = new File(PICDIR+File.separatorChar+fileName);
        boolean result=true;
        try {
            if (!path.exists()){
                path.mkdirs();
            }
            if(!targetFile.exists()){
                multipartFile.transferTo(targetFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        UploadPic upload = new UploadPic();
        if (result) {
            upload.setSuccess(1);
            upload.setMessage("Upload picture success!");
            upload.setUrl(PICDIR + File.separatorChar + fileName);
        }else {
            upload.setSuccess(0);
            upload.setMessage("Upload picture fail!");
            upload.setUrl(PICDIR + File.separatorChar + fileName);
        }
         ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(upload));
    }
   /* @GetMapping("/test")
    public  void testJson(HttpServletResponse response) throws Exception{
        UploadPic project=new UploadPic();
        project.setUrl("http");
//        project.setMessage("zhansgan");
//        project.setSuccess(1);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(project));
    }*/
}
