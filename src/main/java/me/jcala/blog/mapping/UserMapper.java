package me.jcala.blog.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by jcala on 2016/7/23
 */
@Repository
@Mapper
public interface UserMapper {
    @Insert("insert into users set username='zzp',password='zzp105'")
    void insert();
}
