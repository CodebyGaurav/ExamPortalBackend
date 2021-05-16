package com.codebygaurav.examserver.exceptionhandler;

public class UserFoundException extends Exception{

	public UserFoundException() {
		// TODO Auto-generated constructor stub
		super("User with this Username is already there in DB !! try with another user");
	}
	
	public UserFoundException(String msg){
		super(msg);
	}
}
