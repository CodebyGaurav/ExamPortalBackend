package com.codebygaurav.examserver.service;

import java.util.Set;

import com.codebygaurav.examserver.entity.exam.Question;
import com.codebygaurav.examserver.entity.exam.Quiz;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getAllQuestions();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long questionId);
	
	public Question get(Long questionsId);

}
