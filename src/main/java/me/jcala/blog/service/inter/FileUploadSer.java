package me.jcala.blog.service.inter;

import me.jcala.blog.domain.Info;
import me.jcala.blog.domain.UploadPic;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadSer {
    UploadPic uploadPic(HttpServletRequest request);
    Info updateAvatar(HttpServletRequest request);
}
