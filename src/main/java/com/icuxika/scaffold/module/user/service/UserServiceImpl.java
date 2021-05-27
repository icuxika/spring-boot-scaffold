package com.icuxika.scaffold.module.user.service;

import com.icuxika.scaffold.annotation.RedisLock;
import com.icuxika.scaffold.module.user.entity.User;
import com.icuxika.scaffold.module.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("redisConnectionFactory1")
    private RedisConnectionFactory redisConnectionFactory1;

    @RedisLock(key = "test")
    @Override
    public void test() {
        try {
            Thread.sleep(1000);
            System.out.println(LocalDateTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String some() {
        String s = "1231254";
        System.out.println(s);
        return s;
    }

    @Override
    public User getUserInfoById(Long userId) {
        return userMapper.selectByPrimaryKey(userId).orElse(null);
    }
}
