package me.jcala.blog.service;

import me.jcala.blog.domain.Info;
import me.jcala.blog.service.inter.InfoSerInter;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class InfoSer implements InfoSerInter {
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
