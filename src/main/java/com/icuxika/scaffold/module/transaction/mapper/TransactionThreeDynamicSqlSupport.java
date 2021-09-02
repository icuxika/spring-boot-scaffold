package com.icuxika.scaffold.module.transaction.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class TransactionThreeDynamicSqlSupport {
    public static final TransactionThree transactionThree = new TransactionThree();

    public static final SqlColumn<Long> id = transactionThree.id;

    public static final SqlColumn<String> nickname = transactionThree.nickname;

    public static final SqlColumn<String> avatar = transactionThree.avatar;

    public static final class TransactionThree extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public TransactionThree() {
            super("t_transaction_three");
        }
    }
}