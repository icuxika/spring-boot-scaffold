-- 事务传播测试
create table t_transaction_one
(
    id       bigint(20) not null primary key auto_increment,
    nickname varchar(255),
    avatar   varchar(255)
);

create table t_transaction_two
(
    id       bigint(20) not null primary key auto_increment,
    nickname varchar(255),
    avatar   varchar(255)
);

create table t_transaction_three
(
    id       bigint(20) not null primary key auto_increment,
    nickname varchar(255),
    avatar   varchar(255)
);
