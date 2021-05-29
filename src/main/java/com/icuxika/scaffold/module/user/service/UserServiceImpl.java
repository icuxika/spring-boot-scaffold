package com.icuxika.scaffold.module.user.service;

import com.icuxika.scaffold.annotation.RedisLock;
import com.icuxika.scaffold.module.user.entity.User;
import com.icuxika.scaffold.module.user.mapper.UserDynamicSqlSupport;
import com.icuxika.scaffold.module.user.mapper.UserMapper;
import com.icuxika.scaffold.parameter.PageQuery;
import com.icuxika.scaffold.parameter.QueryPage;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public QueryPage<List<User>> getUserList(PageQuery query) {
        long currentPage = query.getCurrentPage() == null ? 1 : query.getCurrentPage();
        long pageSize = query.getPageSize() == null ? 15 : query.getPageSize();
        long total = userMapper.count(CountDSLCompleter.allRows());
        List<User> userList = userMapper.select(s ->
                s.orderBy(query.getDesc() == null ? UserDynamicSqlSupport.id : query.getDesc() ? UserDynamicSqlSupport.id.descending() : UserDynamicSqlSupport.id)
                        .limit(pageSize).offset((currentPage - 1) * pageSize)
        );
        QueryPage<List<User>> result = new QueryPage<>();
        result.setData(userList);
        result.setTotal(total);
        result.setCurrentPage(currentPage);
        result.setPageSize(pageSize);
        return result;
    }
}
