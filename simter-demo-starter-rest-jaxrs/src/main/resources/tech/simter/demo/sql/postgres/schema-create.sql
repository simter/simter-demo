create table demo (
  id   serial primary key,
  name varchar(255) not null,
  remark text
);
comment on table demo is 'Demo';
comment on column demo.name is '名称';