package com.glearning.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glearning.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();
}
