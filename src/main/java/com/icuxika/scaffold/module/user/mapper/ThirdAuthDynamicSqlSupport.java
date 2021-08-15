package com.icuxika.scaffold.module.user.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ThirdAuthDynamicSqlSupport {
    public static final ThirdAuth thirdAuth = new ThirdAuth();

    public static final SqlColumn<Long> id = thirdAuth.id;

    public static final SqlColumn<Long> openId = thirdAuth.openId;

    public static final SqlColumn<Integer> type = thirdAuth.type;

    public static final class ThirdAuth extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> openId = column("open_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public ThirdAuth() {
            super("t_third_auth");
        }
    }
}