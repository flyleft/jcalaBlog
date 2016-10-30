package me.jcala.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import static org.springframework.util.StreamUtils.copy;

/**
 * 文件操作工具类
 */
@Slf4j
public class FileTools {
    /**
     * 获取文件后缀
     */
    public   static String getSuffix(String fileName){
        String[] token = fileName.split("\\.");
        if (token.length>0){
            return token[token.length-1];
        }
        else {
            return "";
        }
    }
    public static boolean isLinuxPath(String path){
        return path.contains("/");
    }

    /**
     * 读取文件为字节数组
     */
    public static byte[] readFileToByteArray(final File file) throws IOException {
        InputStream in = openInputStream(file);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(in, output);
        return output.toByteArray();
    }
    private static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new RuntimeException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }
    public static String updatePic(String restUrl,String picHome,HttpServletRequest request)
            throws Exception {

        MultipartFile multipartFile = getMultipartFile(request);

        //设置图片名称为currentTimeMillis+文件后缀
        String fileName = String.valueOf(System.currentTimeMillis()) + "." +
                FileTools.getSuffix(multipartFile.getOriginalFilename());
        //获取当前年月
        String yearMonth = TimeTools.getYearMonthOfNow();

        //图片存储路径为根路径/年月。比如user/jcala/xmarket/201608
        File path = new File(picHome+File.separatorChar+ yearMonth);

        //合成图片在服务器上的绝对路径
        File targetFile = new File(picHome+File.separatorChar + yearMonth + File.separatorChar + fileName);
        if (!path.exists()) {
            path.mkdirs();
        }
        //保存图片
        multipartFile.transferTo(targetFile);
        return getServerRoot(request) + restUrl + yearMonth + "/" + fileName;
    }

    /**
     * 从HttpServletRequest中获取MultipartFile
     */
    private static MultipartFile getMultipartFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        return multipartRequest.getFile(fileNames.next());
    }

    /**
     * 获取web服务器访问url根路径
     */
    private static String getServerRoot(HttpServletRequest request){
        String serverRoot = "";
        try {
            serverRoot=new URL(request.getScheme(), request.getServerName(), request.getServerPort(),
                    request.getContextPath()).toString();
        } catch (MalformedURLException e) {
            log.warn(e.getLocalizedMessage());
        }
        return serverRoot;
    }
}
