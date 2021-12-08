package com.icuxika.scaffold.module.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icuxika.scaffold.module.blog.entity.Blog;
import com.icuxika.scaffold.module.blog.model.BlogDto;
import com.icuxika.scaffold.parameter.PageQuery;

public interface BlogService extends IService<Blog> {

    Page getBlogPage(PageQuery pageQuery);

    void createBlog(BlogDto blogDto);

}
