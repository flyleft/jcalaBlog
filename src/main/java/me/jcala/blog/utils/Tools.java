package me.jcala.blog.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/9/3.
 */
public class Tools {
    public static Timestamp getTimestamp(){
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return Timestamp.valueOf(nowTime);
    }
    public static List<String> getTagList(String tagStr){
        List<String> tagList=new ArrayList<>();
        StringTokenizer token = new StringTokenizer(tagStr, " ");
        while (token.hasMoreTokens()) {
            tagList.add(token.nextToken());
        }
        return tagList;
    }
}
