package com.icuxika.scaffold.module.auth.service;

import com.icuxika.scaffold.module.auth.entity.GitHubUserInfo;
import com.icuxika.scaffold.module.auth.entity.GiteeUserInfo;
import com.icuxika.scaffold.module.auth.entity.UserToken;

import javax.servlet.http.HttpSession;

public interface AuthService {

    void loginWithGitHub(GitHubUserInfo userInfo, HttpSession session);

    void loginWithGitee(GiteeUserInfo userInfo, HttpSession session);

    UserToken login(String username, String password);

    UserToken register(String username, String password);
}
