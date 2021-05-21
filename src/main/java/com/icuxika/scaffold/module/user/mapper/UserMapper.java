package com.icuxika.scaffold.module.user.mapper;

import com.icuxika.scaffold.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    User getUserById(long id);
}
