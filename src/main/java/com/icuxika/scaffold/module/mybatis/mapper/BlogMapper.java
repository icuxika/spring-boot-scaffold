package com.icuxika.scaffold.module.mybatis.mapper;

import com.icuxika.scaffold.module.mybatis.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    Blog selectByPrimaryKey(Long id);

    List<Blog> selectAll();

    int updateByPrimaryKey(Blog record);

    Blog selectBlogJoinedWithPostsAndAuthor(Long id);

    List<Blog> findActiveBlogLike(Long id, Integer authorId, String title);

    int insertBatch(List<Blog> blogList);
}