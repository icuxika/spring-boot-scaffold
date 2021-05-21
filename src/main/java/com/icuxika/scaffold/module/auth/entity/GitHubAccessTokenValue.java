package com.icuxika.scaffold.module.auth.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GitHubAccessTokenValue {

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "GitHubAccessTokenValue{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
