package com.icuxika.scaffold.module.user.feign;

import com.icuxika.scaffold.annotation.SimpleFeignClient;

@SimpleFeignClient(name = "user")
public interface UserClient {

    String data();
}
