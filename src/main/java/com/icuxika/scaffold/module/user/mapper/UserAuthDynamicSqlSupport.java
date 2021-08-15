package com.icuxika.scaffold.module.user.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserAuthDynamicSqlSupport {
    public static final UserAuth userAuth = new UserAuth();

    public static final SqlColumn<Long> id = userAuth.id;

    public static final SqlColumn<Long> userId = userAuth.userId;

    public static final SqlColumn<Long> authId = userAuth.authId;

    public static final SqlColumn<Integer> type = userAuth.type;

    public static final class UserAuth extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> authId = column("auth_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public UserAuth() {
            super("t_user_auth");
        }
    }
}