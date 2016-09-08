package me.jcala.blog.mapping;

import me.jcala.blog.domain.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/9/8.
 */
@Repository
@Mapper
public interface InfoMapper {

    @Select({
        "select username,sex,real_name,",
            "email,website,github,",
            "linkedin,twitter,pro_exp,",
            "edu_exp,advantage ",
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

}
