package com.codebygaurav.examserver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

import com.codebygaurav.examserver.entity.exam.Question;
import com.codebygaurav.examserver.entity.exam.Quiz;
import com.codebygaurav.examserver.service.QuestionService;
import com.codebygaurav.examserver.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired 
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add Question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
		
	}
	
	//update Question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	//Get Question
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId){
//		Quiz quiz = new Quiz();
//		quiz.setQid(qId);
//		Set<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
//		
//		return ResponseEntity.ok(questions);
		
		Quiz quiz = this.quizService.getQuiz(qId);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestion())) {
			list =list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion()+1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	//Get SIngle Question
	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}
	
	//delete question
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}
	
}
