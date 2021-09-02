-- 业务数据
alter table t_business_property
    add column is_available tinyint(1) default 0 not null;

alter table t_business_property
    add column is_unsigned tinyint(1) unsigned default 0 not null;

alter table t_business_property
    add column type tinyint(2) default 0 not null;
