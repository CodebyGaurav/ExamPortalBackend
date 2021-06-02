package com.codebygaurav.examserver.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codebygaurav.examserver.entity.exam.Question;
import com.codebygaurav.examserver.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz); //Quiz property Custom finder method

}
