package com.icuxika.scaffold.module.user.controller;

import com.icuxika.scaffold.LibraryKt;
import com.icuxika.scaffold.annotation.ApiReturn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @ApiReturn(disable = false)
    @RequestMapping("hello")
    public String hello() {
        return LibraryKt.hello();
    }
}
