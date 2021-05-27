package com.icuxika.scaffold.module.user.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class LocalAuthDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    public static final LocalAuth localAuth = new LocalAuth();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source field: t_local_auth.id")
    public static final SqlColumn<Long> id = localAuth.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source field: t_local_auth.username")
    public static final SqlColumn<String> username = localAuth.username;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source field: t_local_auth.password")
    public static final SqlColumn<String> password = localAuth.password;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source field: t_local_auth.phone")
    public static final SqlColumn<String> phone = localAuth.phone;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    public static final class LocalAuth extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public LocalAuth() {
            super("t_local_auth");
        }
    }
}