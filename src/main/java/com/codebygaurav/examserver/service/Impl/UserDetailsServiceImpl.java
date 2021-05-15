package com.codebygaurav.examserver.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codebygaurav.examserver.entity.User;
import com.codebygaurav.examserver.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepository.findByUsername(username);
		if (user==null) {
			System.out.println("User not Found");
			throw new UsernameNotFoundException("No user found !!");
		}
		
		return user;
	}

}
