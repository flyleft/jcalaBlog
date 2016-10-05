package me.jcala.blog.mapping;

import me.jcala.blog.domain.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mybatis的mapper
 * 映射所有项目操作的sql语句
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

    @Select({
            "select id,name,url,tech,desp",
            "from project",
            "where id = #{id}",
            "limit 1"
    })
    Project selectById(@Param("id") int id) throws Exception;

    @Select({
            "select id,name,url",
            "from project",
            "limit #{st},10"
    })
    List<Project> adminSelect(@Param("st") int start) throws Exception;

    @Select("select count(*) from project")
    int count() throws Exception;

    @Insert({
            "insert into project",
            "set name= #{p.name},",
            "url = #{p.url},date=#{p.date},",
            "tech=#{p.tech},desp=#{p.desp}"
    })
    void insert(@Param("p") Project project) throws Exception;



    @Delete({
            "delete from project",
            "where id = #{id}",
            "limit 1"
    })
    void delete(@Param("id") int id) throws Exception;


    @Update({
            "update project",
            "set name= #{p.name},url = #{p.url},",
            "tech=#{p.tech},desp=#{p.desp}",
            "where id = #{p.id}",
            "limit 1"
    })
    void Update(@Param("p") Project project) throws Exception;
}
