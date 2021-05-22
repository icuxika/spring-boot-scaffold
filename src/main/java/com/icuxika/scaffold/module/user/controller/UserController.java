package com.icuxika.scaffold.module.user.controller;

import com.icuxika.scaffold.LibraryKt;
import com.icuxika.scaffold.annotation.ApiReturn;
import com.icuxika.scaffold.module.user.feign.UserClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private UserClient userClient;

    @ApiReturn(disable = false)
    @RequestMapping("hello")
    public String hello() {
        return LibraryKt.hello();
    }

    @ApiReturn(disable = true)
    @RequestMapping("test")
    public void test() {
        System.out.println(userClient.data());
    }

    @ApiReturn(disable = true)
    @RequestMapping("getBeans")
    public String[] getBeans() {
        return applicationContext.getBeanDefinitionNames();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
