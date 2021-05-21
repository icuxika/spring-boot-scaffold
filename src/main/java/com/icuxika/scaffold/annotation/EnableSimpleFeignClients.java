package com.icuxika.scaffold.annotation;

import com.icuxika.scaffold.registrar.SimpleFeignClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SimpleFeignClientsRegistrar.class)
public @interface EnableSimpleFeignClients {
}
