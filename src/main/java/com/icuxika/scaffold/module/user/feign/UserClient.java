package com.icuxika.scaffold.module.user.feign;

import com.icuxika.scaffold.annotation.SimpleFeignClient;
import com.icuxika.scaffold.annotation.SimpleRequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SimpleFeignClient(name = "user", baseUrl = "http://localhost:8889")
public interface UserClient {

    @SimpleRequestMapping(path = "/hello/beans", method = RequestMethod.GET)
    String data();
}
