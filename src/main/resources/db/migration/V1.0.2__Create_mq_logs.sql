-- 消息队列消息发送日志记录
create table t_mq_message_send_log
(
    id         bigint(20) not null primary key auto_increment,
    messageId  bigint(20) not null,
    status     int default 0,
    exchange   varchar(255),
    routingKey varchar(255),
    count      int,
    createTime datetime,
    updateTime datetime
);