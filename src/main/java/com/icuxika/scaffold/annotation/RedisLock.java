package com.icuxika.scaffold.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * lockKey
     */
    String key();

    /**
     * 超时时间，单位秒
     */
    long expireIn() default 10;

    String error() default "服务器异常，请稍后再试！";
}
