package com.icuxika.scaffold.module.user.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class ThirdAuthDynamicSqlSupport {
    public static final ThirdAuth thirdAuth = new ThirdAuth();

    public static final SqlColumn<Long> id = thirdAuth.id;

    public static final SqlColumn<String> openId = thirdAuth.openId;

    public static final SqlColumn<Integer> type = thirdAuth.type;

    public static final class ThirdAuth extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> openId = column("open_id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public ThirdAuth() {
            super("t_third_auth");
        }
    }
}