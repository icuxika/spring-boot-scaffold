package com.icuxika.scaffold.module.user.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class LocalAuthDynamicSqlSupport {
    public static final LocalAuth localAuth = new LocalAuth();

    public static final SqlColumn<Long> id = localAuth.id;

    public static final SqlColumn<String> username = localAuth.username;

    public static final SqlColumn<String> password = localAuth.password;

    public static final SqlColumn<String> phone = localAuth.phone;

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