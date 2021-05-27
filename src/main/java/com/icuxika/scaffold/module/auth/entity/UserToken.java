package com.icuxika.scaffold.module.auth.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserToken implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 凭证过期时间
     */
    private LocalDateTime expireIn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(LocalDateTime expireIn) {
        this.expireIn = expireIn;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", expireIn=" + expireIn +
                '}';
    }
}
