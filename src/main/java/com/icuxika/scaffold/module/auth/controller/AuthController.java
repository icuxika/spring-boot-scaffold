package com.icuxika.scaffold.module.auth.controller;

import com.icuxika.scaffold.module.auth.entity.Login;
import com.icuxika.scaffold.module.auth.entity.UserToken;
import com.icuxika.scaffold.module.auth.service.AuthService;
import com.icuxika.scaffold.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth/common")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public String login(@RequestBody Login login, HttpSession session) {
        UserToken userToken = authService.login(login.getUsername(), login.getPassword());
        session.setAttribute(TokenAuthenticationFilter.SESSION_ATTRIBUTE_USER_TOKEN, userToken);
        return "登录成功";
    }

    @PostMapping("register")
    public String register(@RequestBody Login login, HttpSession session) {
        UserToken userToken = authService.register(login.getUsername(), login.getPassword());
        session.setAttribute(TokenAuthenticationFilter.SESSION_ATTRIBUTE_USER_TOKEN, userToken);
        return "注册成功";
    }
}
