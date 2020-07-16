CREATE DATABASE IF NOT EXISTS dev;
CREATE DATABASE IF NOT EXISTS test;
USE dev;

create table user(
  id int not null primary key,
  name varchar(20),
  password varchar(20)
)