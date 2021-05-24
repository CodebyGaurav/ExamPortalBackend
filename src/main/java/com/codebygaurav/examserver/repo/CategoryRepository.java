package com.codebygaurav.examserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebygaurav.examserver.entity.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
