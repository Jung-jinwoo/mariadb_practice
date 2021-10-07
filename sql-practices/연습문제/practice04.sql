-- 문제1. 
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까? 
select count(e.emp_no)
from employees e, salaries s
where e.emp_no = s.emp_no
and s.to_date = '9999-01-01'
and s.salary > (select avg(salary) from salaries s where s.to_date = '9999-01-01');

 

-- 문제2.  
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.  
select rs.emp_no, concat(rs.first_name, ' ',rs.last_name), d.dept_name, max(rs.salary)
from dept_emp de, departments d, (select e.emp_no, e.first_name, e.last_name, s.salary from employees e, salaries s where e.emp_no = s.emp_no) rs
where de.dept_no = d.dept_no 
and  rs.emp_no = de.emp_no
group by dept_name
having max(rs.salary)
order by max(rs.salary) desc;
 

-- 문제3. 
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요  
select rs.emp_no, concat(rs.first_name, ' ', rs.last_name), rs.salary
from dept_emp de, departments d, (select e.emp_no, e.first_name, e.last_name, s.salary from employees e, salaries s where e.emp_no = s.emp_no) rs
where de.dept_no = d.dept_no 
and  rs.emp_no = de.emp_no
and de.to_date = '9999-01-01'
group by dept_name
having rs.salary > avg(rs.salary);
 

-- 문제4. 
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요. 
 select e.emp_no, concat(e.first_name, ' ', e.last_name), concat(mg.first_name, ' ', mg.last_name), mg.dept_name
 from employees e, (
	 select e.emp_no, e.first_name, e.last_name, d.dept_name
	 from employees e, departments d, dept_manager dm
	 where e.emp_no = dm.emp_no
	 and d.dept_no = dm.dept_no) mg;



-- 문제5. 
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요. 
select e.emp_no, concat(e.first_name,' ', e.last_name), t.title, s.salary
from employees e, titles t, salaries s, departments d, dept_emp de,
	(select rs.emp_no, max(rs.avg_salary), rs.dept_no
	from (
		select e.emp_no, d.dept_no, avg(s.salary) as avg_salary
		from employees e, departments d, dept_emp de, salaries s
		where e.emp_no = de.emp_no
		and d.dept_no = de.dept_no
		and e.emp_no = s.emp_no
		group by d.dept_name
		having avg(s.salary)) rs) p
where e.emp_no = t.emp_no
and e.emp_no = s.emp_no
and e.emp_no = de.emp_no
and de.dept_no = d.dept_no
and d.dept_no = p.dept_no
order by s.salary;
 

-- 문제6. 
-- 평균 연봉이 가장 높은 부서는?  
select rs.dept_name, max(rs.avg_salary)
	from (
		select e.emp_no, d.dept_no, d.dept_name, avg(s.salary) as avg_salary
		from employees e, departments d, dept_emp de, salaries s
		where e.emp_no = de.emp_no
		and d.dept_no = de.dept_no
		and e.emp_no = s.emp_no
		group by d.dept_name
		having avg(s.salary)) rs;


-- 문제7. 
-- 평균 연봉이 가장 높은 직책? 
select rs.title, max(rs.avg_salary)
	from (
	select e.emp_no, t.title, avg(salary) as avg_salary
	from employees e, titles t, salaries s
	where e.emp_no = t.emp_no
	and e.emp_no = s.emp_no
	group by t.title
	having avg(salary)) rs;
 

-- 문제8. 
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은? 
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다. 
select d.dept_name, concat(e.first_name, ' ', e.last_name), es.salary, concat(em.first_name, ' ', em.last_name), ms.salary
from employees e, dept_emp de, departments d, salaries es, dept_manager dm, salaries ms, employees em
where e.emp_no = es.emp_no
and e.emp_no = de.emp_no
and de.dept_no = dm.dept_no
and d.dept_no = dm.dept_no
and dm.emp_no = em.emp_no
and em.emp_no = ms.emp_no
and es.to_date = '9999-01-01'
and ms.to_date = '9999-01-01'
and es.salary > ms.salary;
