package me.jcala.blog.utils;

/**
 * 文件操作工具类
 */
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
}
