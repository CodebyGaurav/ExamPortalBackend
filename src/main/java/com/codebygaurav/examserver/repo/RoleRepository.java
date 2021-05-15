package com.codebygaurav.examserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebygaurav.examserver.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	
	
}
