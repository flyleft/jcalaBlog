package me.jcala.blog.service;

import me.jcala.blog.domain.UploadPic;
import me.jcala.blog.service.inter.FileUploadSerInter;
import me.jcala.blog.utils.FileTools;
import me.jcala.blog.utils.TimeTools;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/9/25.
 */
@Service
public class FileUploadSer implements FileUploadSerInter{
    //设置文件上传目录为操作系统目录/blog/pic
    private static final String IMGDIR=System.getProperties().getProperty("user.home")+
            File.separatorChar+"blog"+File.separatorChar+"img";
    private static final String SEP=File.separator;
    private static final String NGINXIMG="http://127.0.0.1:8090/img/";
    @Override
    public UploadPic uploadPic(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
        //获得文件原始名称
        String fileName = String.valueOf(System.currentTimeMillis())+"."+
                FileTools.getSuffix(multipartFile.getOriginalFilename());
        String yearMonth= TimeTools.getYearMonthOfNow();
        File path=new File(IMGDIR+SEP+yearMonth);
        File targetFile = new File(IMGDIR+SEP+yearMonth+SEP+fileName);
        final UploadPic upload = new UploadPic();
        boolean result=true;
        String errorMsg=null;
        try {
            if (!path.exists()){
                path.mkdirs();
            }
            multipartFile.transferTo(targetFile);
        } catch (Exception e) {
            errorMsg=e.getMessage();
            result=false;
        }
        if (result) {
            upload.setSuccess(1);
            upload.setMessage("Upload picture success!");
            upload.setUrl(NGINXIMG +yearMonth+"/"+ fileName);
        }else {
            upload.setSuccess(0);
            upload.setMessage("Upload picture fail!"+errorMsg);
            upload.setUrl("");
        }
        return upload;
    }
}
