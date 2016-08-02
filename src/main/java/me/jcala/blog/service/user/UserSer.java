package me.jcala.blog.service.user;

import me.jcala.blog.service.inter.UserSerInter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
@Service
public class UserSer implements UserSerInter {
    @Override
    public List<String> aboutMe() {
        return null;
    }

    @Override
    public List<String> experience() {
        return null;
    }
}
