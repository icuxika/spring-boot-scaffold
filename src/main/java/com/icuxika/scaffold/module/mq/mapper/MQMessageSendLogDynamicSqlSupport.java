package com.icuxika.scaffold.module.mq.mapper;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MQMessageSendLogDynamicSqlSupport {
    public static final MQMessageSendLog MQMessageSendLog = new MQMessageSendLog();

    public static final SqlColumn<Long> id = MQMessageSendLog.id;

    public static final SqlColumn<String> messageId = MQMessageSendLog.messageId;

    public static final SqlColumn<Integer> status = MQMessageSendLog.status;

    public static final SqlColumn<String> exchange = MQMessageSendLog.exchange;

    public static final SqlColumn<String> routingKey = MQMessageSendLog.routingKey;

    public static final SqlColumn<Integer> count = MQMessageSendLog.count;

    public static final SqlColumn<Date> createTime = MQMessageSendLog.createTime;

    public static final SqlColumn<Date> updateTime = MQMessageSendLog.updateTime;

    public static final class MQMessageSendLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> messageId = column("message_id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<String> exchange = column("exchange", JDBCType.VARCHAR);

        public final SqlColumn<String> routingKey = column("routing_key", JDBCType.VARCHAR);

        public final SqlColumn<Integer> count = column("count", JDBCType.INTEGER);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public MQMessageSendLog() {
            super("t_mq_message_send_log");
        }
    }
}