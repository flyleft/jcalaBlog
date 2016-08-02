package me.jcala.blog.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jcala on 2016/7/23
 */
@Repository
@Mapper
public interface UserMapper {
     String aboutMe="";
     String experience="";
    @Select(aboutMe)
    List<String> getAboutMe();
    @Select(experience)
    List<String> getExperience();
}
