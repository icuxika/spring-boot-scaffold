package com.icuxika.scaffold.module.transaction.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class TransactionTwoDynamicSqlSupport {
    public static final TransactionTwo transactionTwo = new TransactionTwo();

    public static final SqlColumn<Long> id = transactionTwo.id;

    public static final SqlColumn<String> nickname = transactionTwo.nickname;

    public static final SqlColumn<String> avatar = transactionTwo.avatar;

    public static final class TransactionTwo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public TransactionTwo() {
            super("t_transaction_two");
        }
    }
}