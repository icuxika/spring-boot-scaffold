create table user
(
    id       bigint(10) not null primary key auto_increment,
    username varchar(50)  not null,
    password varchar(255) not null
);

insert into user (username, password)
values ('icuxika', 'icuxika');