package com.icuxika.scaffold;

import com.icuxika.scaffold.annotation.EnableSimpleFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableSimpleFeignClients
public class SpringBootScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootScaffoldApplication.class, args);
    }

}
