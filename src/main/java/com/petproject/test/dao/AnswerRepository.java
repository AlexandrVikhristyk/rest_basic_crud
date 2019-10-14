package com.petproject.test.dao;

import com.petproject.test.entity.Answer;
import com.petproject.test.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);
}
