package com.icuxika.scaffold.module.mybatis.mapper;

import com.icuxika.scaffold.module.mybatis.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogMapperTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void selectBlogJoinedWithPostsAndAuthor() {
        Blog blog = blogMapper.selectBlogJoinedWithPostsAndAuthor(1L);
        System.out.println(blog);


        // TODO
    }

    @Test
    void findActiveBlogLike() {
        List<Blog> blogs = blogMapper.findActiveBlogLike(null, null, null);
        System.out.println(blogs);
    }

    @Test
    void insertBatch() {
        List<Blog> blogList = new ArrayList<>();

        Blog blog1 = new Blog();
        blog1.setAuthorId(199);
        blog1.setTitle("one");

        Blog blog2 = new Blog();
        blog2.setAuthorId(199);
        blog2.setTitle("one");

        int count = blogMapper.insertBatch(blogList);
        System.out.println(count);
        System.out.println(blog1.getId());
        System.out.println(blog2.getId());
    }
}