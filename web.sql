-- table 생성
create table users(
	no integer auto_increment primary key,
    id varchar(20) unique not null,
    password varchar(20) not null,
    name varchar(20),
    gender varchar(20)
);

create table guestbook(
		no integer auto_increment primary key,
        name varchar(80),
        password varchar(20),
        content varchar(2000),
        reg_date datetime
);

insert into users
values (null, '11', '12', '23', 'female');

insert into guestbook
values (null, '이은빈', '1234',  '자고싶다', now());

select * 
from users;

select *
from guestbook;