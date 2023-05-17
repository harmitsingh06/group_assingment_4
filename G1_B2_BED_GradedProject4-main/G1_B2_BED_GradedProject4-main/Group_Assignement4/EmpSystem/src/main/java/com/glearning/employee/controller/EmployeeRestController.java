package com.glearning.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glearning.employee.entity.Employee;
import com.glearning.employee.entity.Role;
import com.glearning.employee.entity.User;
import com.glearning.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role addRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	@GetMapping("/employees")
	public List<Employee> findAll(@AuthenticationPrincipal UserDetails currentUser) {
		System.out.println(currentUser.getUsername());
		return employeeService.findAll();
	}

	@GetMapping("/employees/{theId}")
	public Employee getEmployee(@PathVariable int theId) {

		Employee theEmployee = employeeService.findById(theId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + theId);
		}

		return theEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theEmployee.setId(0);

		employeeService.save(theEmployee);

		return theEmployee;
	}

	@PutMapping("employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

		return theEmployee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee tempEmployee = employeeService.findById(employeeId);
		System.out.println(employeeId);

		// throw exception if null

		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName() {
		return employeeService.sortByFirstNameAsc();
	}
}
