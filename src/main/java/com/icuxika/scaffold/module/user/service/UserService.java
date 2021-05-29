package com.icuxika.scaffold.module.user.service;

import com.icuxika.scaffold.module.user.entity.User;
import com.icuxika.scaffold.parameter.PageQuery;
import com.icuxika.scaffold.parameter.QueryPage;

import java.util.List;

public interface UserService {

    void test();

    String some();

    User getUserInfoById(Long userId);

    QueryPage<List<User>> getUserList(PageQuery query);
}
