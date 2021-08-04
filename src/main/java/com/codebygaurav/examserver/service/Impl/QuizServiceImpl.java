package com.codebygaurav.examserver.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebygaurav.examserver.entity.exam.Category;
import com.codebygaurav.examserver.entity.exam.Quiz;
import com.codebygaurav.examserver.repo.QuizRepository;
import com.codebygaurav.examserver.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired 
	private QuizRepository quizRepository;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		// TODO Auto-generated method stub
		return new HashSet<Quiz>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub
		this.quizRepository.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepository.findByCategory(category);
	}

	//get Active Quizzes

	@Override
	public List<Quiz> getActiveQuizzes() {
		// TODO Auto-generated method stub
		return this.quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizRepository.findByCategoryAndActive(c, true);
	}
	
	
	
}
