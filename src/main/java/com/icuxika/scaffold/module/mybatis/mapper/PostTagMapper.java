package com.icuxika.scaffold.module.mybatis.mapper;

import com.icuxika.scaffold.module.mybatis.entity.PostTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostTagMapper {
    int deleteByPrimaryKey(@Param("postId") Integer postId, @Param("tagId") Integer tagId);

    int insert(PostTag record);

    List<PostTag> selectAll();
}