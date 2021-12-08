package com.icuxika.scaffold.module.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icuxika.scaffold.config.ApiData;
import com.icuxika.scaffold.module.blog.model.BlogDto;
import com.icuxika.scaffold.module.blog.service.BlogService;
import com.icuxika.scaffold.parameter.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public Page page(PageQuery pageQuery) {
        return blogService.getBlogPage(pageQuery);
    }

    @PostMapping
    public ApiData<Void> createBlog(@RequestBody BlogDto blogDto) {
        blogService.createBlog(blogDto);
        return ApiData.ok("创建成功");
    }

}
