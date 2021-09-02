-- 业务数据
alter table t_business_property
    add column detail_date datetime(3) not null default current_timestamp (3) on update current_timestamp(3);