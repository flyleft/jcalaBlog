package me.jcala.blog.utils;

/**
 * Created by Administrator on 2016/9/25.
 */
public class FileTools {
    public   static String getSuffix(String fileName){
        String[] token = fileName.split("\\.");
        if (token.length>0){
            return token[token.length-1];
        }
        else {
            return "";
        }
    }
}
