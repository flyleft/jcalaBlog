package me.jcala.blog.service;

import me.jcala.blog.domain.UploadPic;
import me.jcala.blog.service.inter.FileUploadSerInter;
import me.jcala.blog.utils.FileTools;
import me.jcala.blog.utils.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FileUploadSer implements FileUploadSerInter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadSer.class);
    //设置文件上传目录为操作系统目录/blog/pic
    private static final String IMGDIR = System.getProperties().getProperty("user.home") +
            File.separatorChar + "blog" + File.separatorChar + "img";
    private static final String NGINXIMG = "http://127.0.0.1:8090/img/";

    @Override
    public UploadPic uploadPic(HttpServletRequest request) {
        MultipartFile multipartFile = getMultipartFile(request);
        //设置图片名称
        String fileName = String.valueOf(System.currentTimeMillis()) + "." +
                FileTools.getSuffix(multipartFile.getOriginalFilename());
        String yearMonth = TimeTools.getYearMonthOfNow();
        File path = new File(IMGDIR + File.separatorChar + yearMonth);
        File targetFile = new File(IMGDIR + File.separatorChar + yearMonth + File.separatorChar + fileName);
        final UploadPic upload = new UploadPic();
        boolean result = true;
        String errorMsg = null;
        try {
            if (!path.exists()) {
                path.mkdirs();
            }
            multipartFile.transferTo(targetFile);
        } catch (Exception e) {
            errorMsg = e.getMessage();
            LOGGER.error(errorMsg);
            result = false;
        }
        if (result) {
            upload.setSuccess(1);
            upload.setMessage("Upload picture success!");
            upload.setUrl(NGINXIMG + yearMonth + "/" + fileName);
        } else {
            upload.setSuccess(0);
            upload.setMessage("Upload picture fail!" + errorMsg);
            upload.setUrl("");
        }
        return upload;
    }

    private MultipartFile getMultipartFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        return multipartRequest.getFile(fileNames.next());
    }
}
