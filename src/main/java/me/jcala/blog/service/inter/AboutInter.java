package me.jcala.blog.service.inter;

import java.util.List;
import java.util.Map;


/**
 * The interface About inter.
 * About页面的数据获取接口
 */
public interface AboutInter {

    /**
     * Gets experience.
     *
     * @return the experience
     */
    List<String> getExperience();

    /**
     * Gets about me.
     *
     * @return the about me
     */
    List<String> getAboutMe();

    /**
     * Gets my info.
     *
     * @return the my info
     */
    List<Map<String,String>> getMyInfo();
}
