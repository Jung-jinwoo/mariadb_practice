-- select 연습
select * from departments;
select dept_no, dept_name from departments;

-- alias (as 생략가능)
-- ex) employees 테이블에서 직원의 이름, 성별, 입사일을 출력
select first_name as 이름, gender as 성별, hire_date as 입사일 from employees;

-- ex2) employees 테이블에서 직원의 이름, 성별, 입사일을 출력 + concat 함수
select concat(first_name,' ',last_name) as 이름, gender as 성별, hire_date as 입사일 from employees;

-- distinct
-- 예제: title 테이블에서 모든 직급의 이름을 출력
select concat(first_name," ", last_name) as 이름, title as 직급 from employees, titles where employees.emp_no = titles.emp_no;

select title from titles limit 0, 3;
select title from titles limit 3, 3;
select distinct title from titles;

-- where절 #1
-- ex) 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name," ", last_name) as 이름, gender as 성별, hire_date as 입사일
from employees
where hire_date < '1991-01-01'
order by hire_date desc;

-- where절 #2 : 논리 연산자
-- ex) 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select concat(first_name," ", last_name) as 이름, gender as 성별, hire_date as 입사일
from employees
where hire_date < '1989-01-01' and gender = 'F'
order by hire_date desc;

-- where절 #3: in
-- ex) dept_emp 테이블에서 부서번호가  d005나 d009에 속한 사원의 사번, 부서번호 출력
select dept_no, emp_no from dept_emp where dept_no in ('d005', 'd009');

-- where절 #4: LIKE 검색
-- ex) 1989년에 입사한 직원의 이름, 입사일 출력
select concat(first_name," ", last_name), hire_date 
from employees 
where hire_date between '1989-01-01' and '1989-12-31'
order by hire_date asc;

select concat(first_name," ", last_name), hire_date 
from employees 
where hire_date like '1989%'
order by hire_date asc;

-- order by 절 #1
-- ex) 남자 직원의 전체 이름, 성별, 입사일을 입사일 순으로 출력
select first_name, gender, hire_date
from employees
where gender ='m'
order by hire_date;

-- ex2) 직원들의 사번, 월급을 월급, 사번 순으로 출력
select emp_no, salary, from_date, to_date
from salaries
order by emp_no asc, salary desc;

