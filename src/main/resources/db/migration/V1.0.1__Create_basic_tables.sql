-- 用户表
create table t_user
(
    id       bigint(20) not null primary key auto_increment,
    nickname varchar(255),
    avatar   varchar(255)
);

-- 用户登录表
create table t_user_auth
(
    id      bigint(20) not null primary key auto_increment,
    user_id bigint(20) not null,
    auth_id bigint(20) not null,
    type    int not null
);

-- 本地登录
create table t_local_auth
(
    id       bigint(20) not null primary key auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    phone    varchar(11)  not null
);

-- 第三方登录
create table t_third_auth
(
    id      bigint(20) not null primary key auto_increment,
    open_id bigint(20) not null,
    type    int not null
);
