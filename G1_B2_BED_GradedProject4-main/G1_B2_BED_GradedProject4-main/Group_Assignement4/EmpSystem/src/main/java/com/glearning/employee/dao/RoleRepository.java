package com.glearning.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glearning.employee.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	// Optional<Role> findByName(String name);

}
