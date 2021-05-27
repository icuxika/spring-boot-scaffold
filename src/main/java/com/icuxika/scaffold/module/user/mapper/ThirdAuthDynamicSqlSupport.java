package com.icuxika.scaffold.module.user.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class ThirdAuthDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source Table: t_third_auth")
    public static final ThirdAuth thirdAuth = new ThirdAuth();

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source field: t_third_auth.id")
    public static final SqlColumn<Long> id = thirdAuth.id;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source field: t_third_auth.open_id")
    public static final SqlColumn<Long> openId = thirdAuth.openId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source field: t_third_auth.type")
    public static final SqlColumn<Integer> type = thirdAuth.type;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source Table: t_third_auth")
    public static final class ThirdAuth extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> openId = column("open_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public ThirdAuth() {
            super("t_third_auth");
        }
    }
}