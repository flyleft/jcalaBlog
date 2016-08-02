package me.jcala.blog.service.inter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface UserSerInter {
    List<String> aboutMe() throws Exception;
    List<String> experience() throws Exception;
}
