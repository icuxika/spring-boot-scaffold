package com.icuxika.scaffold.module.transaction.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class TransactionOneDynamicSqlSupport {
    public static final TransactionOne transactionOne = new TransactionOne();

    public static final SqlColumn<Long> id = transactionOne.id;

    public static final SqlColumn<String> nickname = transactionOne.nickname;

    public static final SqlColumn<String> avatar = transactionOne.avatar;

    public static final class TransactionOne extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public TransactionOne() {
            super("t_transaction_one");
        }
    }
}