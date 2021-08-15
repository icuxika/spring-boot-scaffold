-- 消息队列消息发送日志记录
alter table t_mq_message_send_log change messageId message_id bigint(20) not null;
alter table t_mq_message_send_log change routingKey routing_key varchar (255);
alter table t_mq_message_send_log change createTime create_time datetime;
alter table t_mq_message_send_log change updateTime update_time datetime;