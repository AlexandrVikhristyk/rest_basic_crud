package com.petproject.test.dao;

import com.petproject.test.entity.Question;
import com.petproject.test.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByTest(Test test);
}
