-- 消息队列消息发送日志记录
alter table t_mq_message_send_log modify message_id varchar(255) not null;