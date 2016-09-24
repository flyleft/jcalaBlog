package me.jcala.blog.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jcala.blog.domain.PicUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String uploadPic(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
        //获得文件原始名称
        String fileName = multipartFile.getOriginalFilename();
        File path=new File(PICDIR);
        File targetFile = new File(PICDIR+File.separatorChar+fileName);
        try {
            if (!path.exists()){
                path.mkdirs();
            }
            if(!targetFile.exists()){
                multipartFile.transferTo(targetFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("文件名:"+targetFile.getAbsolutePath());
        PicUpload upload=new PicUpload();
        upload.setSuccess(1);
        upload.setMessage("Upload picture success!");
        upload.setUrl(PICDIR+File.separatorChar+fileName);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(upload);
    }
    /**
     * System.out.println("收到图片上传请求!");
     String fileName = file.getOriginalFilename();

     File targetFile = new File(PICDIR+File.separatorChar+fileName);
     boolean result=true;
     try {
     if(!targetFile.exists()){
     targetFile.createNewFile();
     }
     file.transferTo(targetFile);
     } catch (Exception e) {
     result=false;
     e.printStackTrace();
     }
     PicUpload upload=new PicUpload();
     if (result){
     upload.setSuccess(1);
     upload.setMessage("Upload picture success!");
     upload.setUrl(PICDIR+File.separatorChar+fileName);
     System.out.println("图片上传成功:"+targetFile.getAbsolutePath());
     System.out.println("图片上传成功1:"+PICDIR+File.separatorChar+fileName);
     }else {
     upload.setSuccess(0);
     upload.setMessage("Upload picture fail!");
     upload.setUrl("");
     System.out.println("图片上传失败:"+targetFile.getAbsolutePath());
     }
     return upload;
     */
}
