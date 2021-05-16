package com.codebygaurav.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.codebygaurav.examserver.entity.Role;
import com.codebygaurav.examserver.entity.User;
import com.codebygaurav.examserver.entity.UserRole;
import com.codebygaurav.examserver.exceptionhandler.UserFoundException;
import com.codebygaurav.examserver.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	public void run(String... args) throws Exception {
		try {
		
		System.out.println("Starting Code..");
		
		//admin create
		
		User user = new User();
		user.setFirstname("Gaurav");
		user.setLastname("Sharma");
		user.setUsername("CodebyGaurav");
		user.setPassword(this.bCryptPasswordEncoder.encode("codebygaurav"));
		user.setEmail("gaurav@gmail.com");
		user.setProfile("default.png");
		
		
		Role role1 =  new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");

		Set<UserRole> userRoleSet =  new HashSet<UserRole>();
		UserRole userRole = new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		User user1 = this.userService.createUser(user, userRoleSet);
		System.out.println("Username : "+user1.getUsername());
		
		}catch (UserFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
