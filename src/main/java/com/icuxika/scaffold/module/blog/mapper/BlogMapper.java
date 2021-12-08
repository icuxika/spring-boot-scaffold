package com.icuxika.scaffold.module.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icuxika.scaffold.module.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "tBlogMapper")
public interface BlogMapper extends BaseMapper<Blog> {
}
