package me.jcala.blog.service;

import me.jcala.blog.domain.Visiter;
import me.jcala.blog.mapping.VisiterMapper;
import me.jcala.blog.service.inter.MoniterSerInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
@Service
public class MoniterSer implements MoniterSerInter{
    private static final Logger LOGGER = LoggerFactory.getLogger(MoniterSer.class);
    @Autowired
    private VisiterMapper visiterMapper;
    @Override
    public List<Visiter> getVisiters() {
        List<Visiter> visiters=new ArrayList<>();
        try {
            visiters=visiterMapper.select();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return visiters;
    }
}
