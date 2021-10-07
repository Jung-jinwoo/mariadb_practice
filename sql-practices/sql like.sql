-- Basic Query

create table pet(
	name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth DATE,
    death DATE
);

-- scheme 확인
desc pet;


-- 조회
select name, owner, species, gender, birth, death from pet;

-- 삭제
delete from pet where name='댕댕';

-- 데이터 넣기 (생성, create)
insert 
	into pet
    value('알알이', '진우', '술마시면개됨', 'm', '1111-11-11', null);

-- load data local infile
load data local infile 'c:\\pet.txt' into table pet;

-- update death
update pet set death=null where name != 'Bowser';

-- 조건 where
--- Gwen과 함께사는 아이들은?
select name, species, owner from pet where owner = 'Gwen';

--- null 다루기 1 : 살아있는 애들은?
select name, birth, death from pet where death is null;

--- null 다루기 2 : 죽은 애들은?
select name, birth, death from pet where death is not null;

-- like 검색 (패턴 매칭) : 이름이 b로 시작하는 아이들은?
select name from pet where name like 'b%';

-- like 검색 (패턴 매칭) : 이름이 b로 시작하는 아이들중에 이름이 6자인 아이는?
select name from pet where name like 'b_____';

-- 집계 함수
select count(*) from pet;

select count(death) from pet;

