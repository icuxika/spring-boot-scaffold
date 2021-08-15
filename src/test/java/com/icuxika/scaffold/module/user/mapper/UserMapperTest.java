package com.icuxika.scaffold.module.user.mapper;

import com.icuxika.scaffold.module.user.entity.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.insert.render.BatchInsert;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertMultiple() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setNickname("序号" + i);
            user.setAvatar("xxxx");
            list.add(user);
        }
        userMapper.insertMultipleWithGeneratedKeys(list);

        list.forEach(user -> System.out.println(user.getId()));
    }

    @Test
    void insertBatch() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setNickname("Batch: " + i);
            user.setAvatar("xxxx");
            list.add(user);
        }

        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            BatchInsert<User> batchInsert = SqlBuilder.insertBatch(list).into(UserDynamicSqlSupport.user)
                    .map(UserDynamicSqlSupport.id).toProperty("id")
                    .map(UserDynamicSqlSupport.nickname).toProperty("nickname")
                    .map(UserDynamicSqlSupport.avatar).toProperty("avatar")
                    .build().render(RenderingStrategies.MYBATIS3);
            batchInsert.insertStatements().forEach(mapper::insert);
            session.commit();
        }
    }
}