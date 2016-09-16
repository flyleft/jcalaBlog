package me.jcala.blog.mapping;

import me.jcala.blog.domain.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/9/8.
 */
@Repository
@Mapper
public interface InfoMapper {

    @Select({
        "select username,email,",
            "github,twitter",
            "from admin limit 1"
    })
    Info select() throws Exception;

    @Select({
        "select count(*) ",
            "from admin ",
            "where username = #{un} ",
            "and password = #{pw}"
    })
    int selectByPw(@Param("un") String username, @Param("pw") String password) throws Exception;

    @Update({
        "update admin set username = #{if.username},",
                "email= #{if.email},github=#{if.github},",
                "twitter=#{if.twitter} limit 1",
    })
    void update(@Param("if") Info info);

    @Select({
            "select count(*) ",
            "from admin ",
            "where password = #{pw}"
    })
    int selectByOldPass(@Param("pw") String oldPass) throws Exception;

    @Update({
        "update admin set",
            "password = #{np} ",
            "limit 1"
    })
    int updataPass(@Param("np") String newPass) throws Exception;

    @Select("select md from admin limit 1")
    String selectMd() throws Exception;

    @Update({
            "update admin ",
            "set md = #{if.md},",
            "resume = #{if.resume} ",
            "limit 1"
    })
    void updateResume(@Param("if") Info info) throws Exception;

    @Select("select resume from admin limit 1")
    String getResume() throws Exception;
}
