create table demo (
  id     serial primary key,
  name   varchar(255) not null,
  status smallint     not null check (status in (2, 4)),
  remark text
);
comment on table demo is 'Demo';
comment on column demo.name is 'Name';
comment on column demo.status is 'Status: 2-Enabled, 4-Disabled';