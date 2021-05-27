package com.icuxika.scaffold.aspect;

import com.icuxika.scaffold.annotation.RedisLock;
import com.icuxika.scaffold.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Component
@Aspect
public class RedisLockAspect {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Pointcut("@annotation(com.icuxika.scaffold.annotation.RedisLock)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        RedisLock redisLock = methodSignature.getMethod().getAnnotation(RedisLock.class);
        Lock lock = redisLockRegistry.obtain(redisLock.key());
        try {
            if (lock.tryLock(redisLock.expireIn(), TimeUnit.SECONDS)) {
                return pjp.proceed();
            }
            throw new ServiceException(redisLock.error());
        } catch (Throwable e) {
            throw new ServiceException(redisLock.error());
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                throw new ServiceException(redisLock.error());
            }
        }
    }
}
