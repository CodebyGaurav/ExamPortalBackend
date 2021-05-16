package com.codebygaurav.examserver.service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebygaurav.examserver.entity.User;
import com.codebygaurav.examserver.entity.UserRole;
import com.codebygaurav.examserver.exceptionhandler.UserFoundException;
import com.codebygaurav.examserver.repo.RoleRepository;
import com.codebygaurav.examserver.repo.UserRepository;
import com.codebygaurav.examserver.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	//creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		
		if(local != null) {
			System.out.println("User is already there !!");
			throw new UserFoundException();
		}else {
			//user create
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		
		return local;
	}

	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
	}

	
}
