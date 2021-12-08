package com.icuxika.scaffold.module.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icuxika.scaffold.module.blog.entity.Blog;
import com.icuxika.scaffold.module.blog.entity.BlogContent;
import com.icuxika.scaffold.module.blog.mapper.BlogMapper;
import com.icuxika.scaffold.module.blog.model.BlogDto;
import com.icuxika.scaffold.module.blog.model.BlogVo;
import com.icuxika.scaffold.parameter.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page getBlogPage(PageQuery pageQuery) {
        Page<Blog> page = new Page<>(pageQuery.getCurrentPage(), pageQuery.getPageSize());
        Page<Blog> blogPage = page(page);
        List<BlogVo> blogVoList = blogPage.getRecords().stream().map(blog -> {
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(blog, blogVo);
            blogVo.setBlogContent(mongoTemplate.findOne(Query.query(Criteria.where("blogId").is(blog.getId())), BlogContent.class));
            return blogVo;
        }).collect(Collectors.toList());
        Page<BlogVo> blogVoPage = new Page<>();
        BeanUtils.copyProperties(blogPage, blogVoPage);
        blogVoPage.setRecords(blogVoList);
        return blogVoPage;
    }

    @Override
    public void createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setUserId(1L);
        blog.setCreateTime(LocalDateTime.now());
        blog.setUpdateTime(LocalDateTime.now());
        save(blog);

        BlogContent blogContent = new BlogContent();
        blogContent.setBlogId(blog.getId());
        blogContent.setTitle(blogDto.getTitle());
        blogContent.setContent(blogDto.getContent());
        mongoTemplate.insert(blogContent);
    }
}
