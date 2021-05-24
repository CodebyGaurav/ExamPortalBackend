package com.codebygaurav.examserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebygaurav.examserver.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
