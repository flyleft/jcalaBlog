package me.jcala.blog.mapping;

import me.jcala.blog.domain.Profile;
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
public interface ProfileMapper {

    @Select({
        "select username,email,",
            "github,weibo,twitter",
            "from admin limit 1"
    })
    Profile select() throws Exception;

    @Select({
        "select count(*) ",
            "from admin ",
            "where username = #{un} ",
            "and password = #{pw}"
    })
    int selectByPw(@Param("un") String username, @Param("pw") String password) throws Exception;

    @Update({
        "update admin set username = #{if.username},",
                "email= #{if.email},weibo=#{if.weibo},",
                "github=#{if.github},twitter=#{if.twitter}",
                "limit 1"
    })
    void update(@Param("if") Profile info);

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
}
