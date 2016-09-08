package me.jcala.blog.service;

import me.jcala.blog.domain.Info;
import me.jcala.blog.service.inter.AdminInfoSerInter;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class AdminInfoSer implements AdminInfoSerInter {
    @Override
    public Info getInfo() {

        return null;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean modifyPw() {
        return false;
    }
}
