package com.icuxika.scaffold.module.user.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9688259+08:00", comments = "Source Table: t_user")
    public static final User user = new User();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9697721+08:00", comments = "Source field: t_user.id")
    public static final SqlColumn<Long> id = user.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9697721+08:00", comments = "Source field: t_user.nickname")
    public static final SqlColumn<String> nickname = user.nickname;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9707734+08:00", comments = "Source field: t_user.avatar")
    public static final SqlColumn<String> avatar = user.avatar;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9697721+08:00", comments = "Source Table: t_user")
    public static final class User extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public User() {
            super("t_user");
        }
    }
}