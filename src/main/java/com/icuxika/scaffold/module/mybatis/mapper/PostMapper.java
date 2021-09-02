package com.icuxika.scaffold.module.mybatis.mapper;

import com.icuxika.scaffold.module.mybatis.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    Post selectByPrimaryKey(Long id);

    List<Post> selectAll();

    int updateByPrimaryKey(Post record);
}