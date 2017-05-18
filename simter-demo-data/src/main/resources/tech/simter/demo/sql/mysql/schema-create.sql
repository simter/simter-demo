create table demo (
  id     integer auto_increment primary key,
  name   varchar(255) not null               comment 'Name',
  status tinyint      not null               comment 'Status: 2-Enabled, 4-Disabled',
  remark text,
  check (status in (2, 4))
) comment = 'Demo';