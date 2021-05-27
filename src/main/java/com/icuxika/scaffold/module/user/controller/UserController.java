package com.icuxika.scaffold.module.user.controller;

import com.icuxika.scaffold.annotation.ApiReturn;
import com.icuxika.scaffold.framework.BasicController;
import com.icuxika.scaffold.module.user.entity.User;
import com.icuxika.scaffold.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends BasicController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ApiReturn(disable = true)
    @RequestMapping("test")
    public void test() {
        userService.test();
    }

    @RequestMapping("some")
    public String some() {
        return userService.some();
    }

    @RequestMapping("getBeans")
    public String[] getBeans() {
        return applicationContext.getBeanDefinitionNames();
    }

    @PostMapping("getUserInfo")
    public User getUserInfo() {
        return userService.getUserInfoById(currentUserId());
    }
}
