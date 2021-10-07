create table member(
	no int(11) not null auto_increment,
	email varchar(200) not null,
	password varchar(64) not null,
	name varchar(100) not null,
	department varchar(100),
	primary key(no)
);
desc member;
alter table member add self_intro text;

alter table member drop juminbunho;

alter table member add join_date datetime not null;

alter table member change department_name department varchar(100) not null;

-- insert
insert
into member(no, email, password, name, department, join_date, self_intro)
values(null,'wlsdn960319@gmail.com', password(1234), '정진우2', '개발팀', now(), null);
select * from member;

-- delete
delete
	from member
    where no = 2;

-- update
update member
set email = 'jinwoo@naver.com', password = password(5678)
where no = 1;

-- transaction
select @@AUTOCOMMIT;
set autocommit=1;

