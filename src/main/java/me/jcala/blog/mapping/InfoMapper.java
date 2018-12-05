package me.jcala.blog.mapping;

import me.jcala.blog.domain.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * mybatis的mapper
 * 映射所有个人信息操作的sql语句
 */
@Repository
@Mapper
public interface InfoMapper {

    @Select({
            "select username,email,",
            "github,twitter,avatar",
            "from admin limit 1"
    })
    Info select();

    @Select({
            "select count(*) ",
            "from admin ",
            "where username = #{un} ",
            "and password = #{pw}"
    })
    int selectByPw(@Param("un") String username, @Param("pw") String password);

    @Select({
            "select count(*) ",
            "from admin ",
            "where password = #{pw}"
    })
    int selectByOldPass(@Param("pw") String oldPass);

    @Select("select md from admin limit 1")
    String selectMd();

    @Select("select resume from admin limit 1")
    String selectResume();

    @Update({
            "update admin set username = #{if.username},",
            "email= #{if.email},github=#{if.github},",
            "twitter=#{if.twitter} limit 1",
    })
    void update(@Param("if") Info info);


    @Update({
            "update admin set",
            "password = #{np} ",
            "limit 1"
    })
    int updataPass(@Param("np") String newPass);

    @Update({
            "update admin ",
            "set md = #{if.md},",
            "resume = #{if.resume} ",
            "limit 1"
    })
    void updateResume(@Param("if") Info info);

    @Update({
            "update admin",
            "set avatar = #{ava}",
            "limit 1"
    })
    void updateAvater(@Param("ava") String avatar);
}
