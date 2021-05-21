package com.icuxika.scaffold.module.auth.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GitHubUserInfo {

    private String login;

    private Long id;

    @JsonAlias("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "GitHubUserInfo{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
