package com.icuxika.scaffold.module.blog.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

public class BlogContent implements Serializable {

    @MongoId
    private String id;

    private Long blogId;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BlogContent{" +
                "id='" + id + '\'' +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                '}';
    }
}
