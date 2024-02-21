-- guest book 계정만들기
create user 'web'@'%' identified by '1234';

-- guest 권한 부여
grant all privileges on guestbook_db.* to 'web'@'%';
flush privileges;

-- 데이터 베이스 생성
create database web_db
			default character set utf8mb4
	collate utf8mb4_general_ci
    default encryption='n'
;

use web_db;
-- table 생성
create table users(
	no integer auto_increment primary key,
    id varchar(20) unique not null,
    password varchar(20) not null,
    name varchar(20),
    gender varchar(20)
);

insert into users
values (null, '11', '12', '23', 'female');

select * 
from users;