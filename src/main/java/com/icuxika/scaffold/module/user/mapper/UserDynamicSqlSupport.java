package com.icuxika.scaffold.module.user.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    public static final User user = new User();

    public static final SqlColumn<Long> id = user.id;

    public static final SqlColumn<String> nickname = user.nickname;

    public static final SqlColumn<String> avatar = user.avatar;

    public static final class User extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public User() {
            super("t_user");
        }
    }
}