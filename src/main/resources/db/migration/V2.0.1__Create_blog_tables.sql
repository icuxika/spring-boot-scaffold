create table t_blog
(
    id          bigint(20) not null primary key auto_increment,
    user_id     bigint(20) not null comment '用户id',
    create_time datetime not null,
    update_time datetime
);