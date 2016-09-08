package me.jcala.blog.service.inter;

import me.jcala.blog.domain.Info;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface InfoSerInter {
    Info getInfo();
    boolean login();
    boolean modifyPw();
}
