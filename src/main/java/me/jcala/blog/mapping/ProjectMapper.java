package me.jcala.blog.mapping;

import me.jcala.blog.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
@Repository
@Mapper
public interface ProjectMapper {

    @Select({
            "select name,url,tech,desp,date",
            "from project ",
            "limit #{st},5"
    })
    List<Project> select(@Param("st") int start) throws Exception;
}
