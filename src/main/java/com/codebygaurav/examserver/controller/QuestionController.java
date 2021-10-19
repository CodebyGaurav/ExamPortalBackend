package com.codebygaurav.examserver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
	
	//Get All Question of any quizid
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId){
//		Quiz quiz = new Quiz();
//		quiz.setQid(qId);
//		Set<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
//		
//		return ResponseEntity.ok(questions);
		
		Quiz quiz = this.quizService.getQuiz(qId);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestion())) {
			list =list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion()+1));
		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	//Get All Question
	@GetMapping("/quiz/all/{qId}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qId") Long qId){
		Quiz quiz = new Quiz();
		quiz.setQid(qId);
		Set<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
		
		return ResponseEntity.ok(questions);
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
	
	
	//eval Quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		System.out.println(questions);
		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		for (Question q : questions) {

			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().trim().equals(q.getGivenAnswer().trim())) {
				correctAnswers++;
				
			double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMark())/questions.size();
//				this.questions[0].quiz.maxMark/this.questions.length
		         marksGot += marksSingle;
			}
			if (q.getGivenAnswer()!=null || !q.getGivenAnswer().trim().equals("")){
	           attempted++;
			}
		}
	
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
	
	
}
