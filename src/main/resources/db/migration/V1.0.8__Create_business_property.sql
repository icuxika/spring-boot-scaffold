-- 业务数据
create table t_business_property
(
    id          bigint(20) unsigned not null primary key auto_increment,
    date        timestamp      default current_timestamp,
    create_time datetime       default current_timestamp,
    update_time datetime       default current_timestamp on update current_timestamp,
    price       decimal(16, 2) default '0.00' not null,
    is_deleted  tinyint unsigned default 0 not null
);