package com.icuxika.scaffold.module.user.entity;

import javax.annotation.Generated;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_user
 */
public class User {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9628072+08:00", comments = "Source field: t_user.id")
    private Long id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.nickname")
    private String nickname;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.avatar")
    private String avatar;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9638095+08:00", comments = "Source field: t_user.id")
    public Long getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.nickname")
    public String getNickname() {
        return nickname;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.avatar")
    public String getAvatar() {
        return avatar;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.964823+08:00", comments = "Source field: t_user.avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}