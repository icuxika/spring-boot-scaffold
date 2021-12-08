package com.icuxika.scaffold.module.blog.model;

import com.icuxika.scaffold.module.blog.entity.Blog;
import com.icuxika.scaffold.module.blog.entity.BlogContent;

public class BlogVo extends Blog {

    private BlogContent blogContent;

    public BlogContent getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(BlogContent blogContent) {
        this.blogContent = blogContent;
    }
}
