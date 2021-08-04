package com.codebygaurav.examserver.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebygaurav.examserver.entity.exam.Category;
import com.codebygaurav.examserver.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findByCategory(Category category);
	
	
	//for user used api to find quizzes
	public List<Quiz> findByActive(Boolean b);

	//for user used api to find quizzes
	public List<Quiz> findByCategoryAndActive(Category c,Boolean b);
	
}
