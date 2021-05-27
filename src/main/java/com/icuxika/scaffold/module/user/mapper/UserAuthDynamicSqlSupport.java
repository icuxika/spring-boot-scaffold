package com.icuxika.scaffold.module.user.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserAuthDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9887722+08:00", comments = "Source Table: t_user_auth")
    public static final UserAuth userAuth = new UserAuth();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.989772+08:00", comments = "Source field: t_user_auth.id")
    public static final SqlColumn<Long> id = userAuth.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.989772+08:00", comments = "Source field: t_user_auth.user_id")
    public static final SqlColumn<Long> userId = userAuth.userId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.989772+08:00", comments = "Source field: t_user_auth.auth_id")
    public static final SqlColumn<Long> authId = userAuth.authId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.989772+08:00", comments = "Source field: t_user_auth.type")
    public static final SqlColumn<Integer> type = userAuth.type;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.989772+08:00", comments = "Source Table: t_user_auth")
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