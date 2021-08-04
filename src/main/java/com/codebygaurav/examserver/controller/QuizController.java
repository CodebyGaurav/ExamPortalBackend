package com.codebygaurav.examserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebygaurav.examserver.entity.exam.Category;
import com.codebygaurav.examserver.entity.exam.Quiz;
import com.codebygaurav.examserver.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired 
	private QuizService quizService;
	
	//add QUiz
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	//update Quiz
	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	//Get Quiz
	@GetMapping("/")
	public ResponseEntity<?> getQuizzes(){
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}		
	
	//Get Single Quiz
	@GetMapping("/{qId}")
	public Quiz getQuizId(@PathVariable("qId") Long qId){
		return this.quizService.getQuiz(qId);
	}
	
	//delete quiz
	@DeleteMapping("/{qId}")
	public void deleteQuiz(@PathVariable("qId") Long qId) {
		this.quizService.deleteQuiz(qId);
	}
	
	//get category wise quiz
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid){
		
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	//get active quizzes
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return this.quizService.getActiveQuizzes();
	}
	
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid){
		
		Category category = new Category();
		category.setCid(cid);
		
		return this.quizService.getActiveQuizzesOfCategory(category);
	}
	
	
}
