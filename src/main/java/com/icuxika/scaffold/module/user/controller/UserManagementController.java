package com.icuxika.scaffold.module.user.controller;

import com.icuxika.scaffold.module.user.entity.User;
import com.icuxika.scaffold.module.user.service.UserService;
import com.icuxika.scaffold.parameter.PageQuery;
import com.icuxika.scaffold.parameter.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("management/user")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @GetMapping("getUserList")
    public QueryPage<List<User>> getUserList(PageQuery query) {
        return userService.getUserList(query);
    }
}
